<template>
  <div class="container">
    <div class="reply-title">
      <h4>댓글 목록</h4>
      <div v-if="loginUserName !== null">
        <button class="btn btn-outline-primary" @click="addComment">Add Comment</button>
      </div>
    </div>
    <div class="d-flex justify-content-center align-items-center">
      <div>
        <ul class="comment-list">
          <hr>
          <li v-if="comments.length === 0" class="no-comments" style="list-style: none">
            댓글이 없습니다
          </li>
          <li v-else v-for="comment in comments" :key="comment.communityBoardCommentId" class="comment-item">
            <div class="comment-content">
              <span class="comment-text">{{ comment.content }}</span>
              <div class="comment-actions">
                <div class="writer-bnt" v-if="loginUserName === comment.userId" style="margin-bottom: 20px">
                  <button class="btn btn-outline-warning" @click="editComment(comment)">수정</button>
                  <button class="btn btn-outline-danger" @click="deleteComment(comment.communityBoardCommentId)">삭제</button>
                </div>
                <div class="comment-details">
                  <span>| 작성자 : {{ comment.userId }} |</span>
                  <span>{{ comment.regDate }}</span>
                </div>
              </div>
            </div>
            <hr>
          </li>
        </ul>
      </div>
      <div v-if="showModal" class="modal">
        <div class="modal-content">
          <span class="close" @click="showModal = false">&times;</span>
          <h2>{{ editMode ? '댓글 수정' : '댓글 작성' }}</h2>
          <br>
          <textarea v-model="currentComment.content"></textarea>
          <br>
          <button class="btn btn-outline-success" @click="saveComment">{{ editMode ? '수정' : '작성' }}</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { onMounted, ref } from "vue";

// 반응형 데이터 선언
const loginUserName = ref('')

// 컴포넌트 마운트 시 sessionStorage에서 loginUser 값 로드
const loadUserFromSession = () => {
  const token = sessionStorage.getItem('access-token')
  if (token) {
    const storedUser = token.split('.')
    if (storedUser.length > 1) {
      loginUserName.value = JSON.parse(atob(storedUser[1]))['id']
    }
  }
}

onMounted(() => {
  loadUserFromSession()
});
</script>

<script>
import replyService from '@/stores/comment.js';

export default {
  data() {
    return {
      comments: [],
      showModal: false,
      editMode: false,
      currentComment: {
        commentId: null,
        content: '',
        communityBoardId: this.boardId,
        userId: ""
      },
      user_id: sessionStorage.getItem('access-token') ? JSON.parse(atob(sessionStorage.getItem('access-token').split('.')[1]))['id'] : null,
      boardId: this.$route.params.id
    };
  },
  created() {
    this.fetchComments();
  },
  methods: {
    fetchComments() {
      replyService.getList({ boardId: this.boardId }, (replyCnt, list) => {
        this.comments = list;
      }, (error) => {
        console.error(error);
      });
    },
    addComment() {
      this.editMode = false;
      this.currentComment = { content: '', communityBoardId: this.boardId, userId: this.user_id };
      this.showModal = true;
    },
    editComment(comment) {
      this.editMode = true;
      this.currentComment = { ...comment };
      this.showModal = true;
    },
    saveComment() {
      if (this.editMode) {
        replyService.update(this.currentComment, () => {
          this.fetchComments();
          this.showModal = false;
        }, (error) => {
          console.error(error);
        });
      } else {
        replyService.add(this.currentComment, () => {
          this.fetchComments();
          this.showModal = false;
        }, (error) => {
          console.error(error);
        });
      }
    },
    deleteComment(commentId) {
      if (confirm("댓글을 삭제하시겠습니까?")) {
        replyService.remove(commentId, () => {
          this.fetchComments();
        }, (error) => {
          console.error(error);
        });
      }
    }
  }
};
</script>

<style scoped>
.modal {
  display: block;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgb(0, 0, 0);
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
  background-color: #fefefe;
  margin: 15% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 80%;
}

.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

.reply-title {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding-right: 15%;
  padding-left: 15%;
}

.comment-list {
  width: 60rem;
  padding-right: 3%;
}

.comment-item {
  display: flex;
  flex-direction: column;
  position: relative;
}

.comment-content {
  display: flex;
  justify-content: space-between;
  flex-grow: 1;
}

.comment-actions {
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  margin-left: 20px;
}

.comment-text {
  max-width: 50ch;
  word-wrap: break-word;
  word-break: break-all;
}

.writer-bnt {
  display: flex;
  gap: 10px;
}

.comment-details {
  display: flex;
  align-items: center;
}

.comment-divider {
  position: absolute;
  bottom: 0;
  width: 100%;
  border: none;
  border-bottom: 1px solid #ccc;
}

.no-comments {
  text-align: center;
  font-size: 1.2rem;
  color: #777;
}

.container {
  margin-bottom: 2%;
}
</style>
