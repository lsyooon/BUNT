import $ from 'jquery';

const REST_BOARD_API = 'http://localhost:8080/api/board/comment';

var replyService = (function() {
    function add(comment, callback, error) {
        console.log("add comment");
        console.log(comment)
        $.ajax({
            type: 'post',
            url: `${REST_BOARD_API}`,
            data: JSON.stringify(comment),
            contentType: 'application/json;charset=UTF-8',
            success: function(result) {
                if (callback) {
                    callback(result);
                }
            },
            error: function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        });
    }

    function getList(param, callback, error) {
        var boardId = param.boardId;
        $.getJSON(`${REST_BOARD_API}/${boardId}`, function(data) {
            if (callback) {
                callback(data.length, data);
            }
        }).fail(function(xhr, status, er) {
            if (error) {
                error(er);
            }
        });
    }

    function remove(commentId, callback, error) {
        $.ajax({
            type: 'delete',
            url: `${REST_BOARD_API}/${commentId}`,
            success: function(result) {
                if (callback) {
                    callback(result);
                }
            },
            error: function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        });
    }

    function update(comment, callback, error){
        console.log(comment)
        $.ajax({
            type: 'put',
            url: `${REST_BOARD_API}/${comment.communityBoardCommentId}`,
            data: JSON.stringify(comment),
            contentType: 'application/json;charset=UTF-8',
            success: function(result) {
                if (callback) {
                    callback(result);
                }
            },
            error: function(xhr, status, er) {
                if (error) {
                    error(er);
                }
            }
        });
    }
    
    return {
        add: add,
        getList: getList,
        remove: remove,
        update: update
    };
})();

export default replyService;
