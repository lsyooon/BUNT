<template>
  <div class="container">
    <div class="reply-title">
      <h4>댓글 목록</h4>
      <div v-if="loginUser !== null">
        <button class="btn btn-outline-primary" @click="addComment">Add Comment</button>
      </div>
    </div>
    <div class="d-flex justify-content-center align-items-center">
      <div>
        <ul class="comment-list">
          <hr>
          <li v-for="comment in comments" :key="comment.communityBoardCommentId" class="comment-item">
            <div class="comment-content">
              <span>{{ comment.content }}</span>
              <div>
                <span>|　작성자 : {{ comment.userId }}　|　</span>
                <span>{{ comment.regDate }}　</span>
              </div>
            </div>
            <div class="writer-bnt" v-if="loginUserName === comment.userId">
              <button class="btn btn-outline-warning" @click="editComment(comment)">수정</button>
              <button class="btn btn-outline-danger" @click="deleteComment(comment.communityBoardCommentId)">삭제</button>
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
import {onMounted, ref} from "vue";

const loginUser = ref(null);
const loginUserName = ref('');
onMounted(() => {
  const storedUser = sessionStorage.getItem('loginUser');
  if (storedUser) {
    loginUser.value = JSON.parse(storedUser);
    loginUserName.value = loginUser.value.id; // 사용자 이름 속성 사용
  }
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
      user_id: sessionStorage.getItem('loginUser') ? JSON.parse(sessionStorage.getItem('loginUser')).id : null,
      boardId: this.$route.params.id
    };
  },
  created() {
    this.fetchComments();
  },
  methods: {
    fetchComments() {
      replyService.getList({boardId: this.boardId}, (replyCnt, list) => {
        this.comments = list;
      }, (error) => {
        console.error(error);
      });
    },
    addComment() {
      this.editMode = false;
      this.currentComment = {content: '', communityBoardId: this.boardId, userId: this.user_id};
      this.showModal = true;
    },
    editComment(comment) {
      this.editMode = true;
      this.currentComment = {...comment};
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
      replyService.remove(commentId, () => {
        this.fetchComments();
      }, (error) => {
        console.error(error);
      });
    }
  }
};
</script>

<style>
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
  justify-content: space-between;
  align-items: center;
  padding-top: 3%;
  position: relative;
}

.comment-content {
  flex-grow: 1;
  display: flex;
  justify-content: space-between;
}

.writer-bnt {
  display: flex;
  gap: 10px;
}

.comment-divider {
  position: absolute;
  bottom: 0;
  width: 100%;
  border: none;
  border-bottom: 1px solid #ccc;
}
</style>
