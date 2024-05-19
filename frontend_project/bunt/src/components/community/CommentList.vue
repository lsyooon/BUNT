<template>
  <div>
    <button @click="addComment">Add Comment</button>
    <ul>
      <li v-for="comment in comments" :key="comment.communityBoardCommentId">
        {{ comment.content }}
        <button @click="editComment(comment)">Edit</button>
        <button @click="deleteComment(comment.communityBoardCommentId)">Delete</button>
        <button @click="prints()">Delete</button>
      </li>
    </ul>

    <div v-if="showModal" class="modal">
      <div class="modal-content">
        <span class="close" @click="showModal = false">&times;</span>
        <h2>{{ editMode ? 'Edit Comment' : 'Add Comment' }}</h2>
        <textarea v-model="currentComment.content"></textarea>
        <button @click="saveComment">{{ editMode ? 'Update' : 'Save' }}</button>
      </div>
    </div>
  </div>
</template>



<script>
import replyService from '@/stores/comment.js';
import { onMounted, ref} from "vue";
const loginUser = ref(null);
const loginUserName = ref('');





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
      user_id: JSON.parse(sessionStorage.getItem('loginUser')).id,
      boardId: this.$route.params.id
    };
  },
  created() {
    this.fetchComments();
  },

  methods: {
    prints(){
      console.log()

    },
    fetchComments() {
      replyService.getList({ boardId: this.boardId }, (replyCnt, list) => {
        this.comments = list;
      }, (error) => {
        console.error(error);
      });
    },
    addComment() {
      this.editMode = false;
      this.currentComment = {  content: '', communityBoardId: this.boardId, userId: this.user_id };
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
</style>
