window.onload = function () {
    // selectBoradList();

    document.querySelector(".board-insert-btn").addEventListener("click", () => {
       const boardTitle = $("input[name=boardTitle]").val()
       const boardContent = $("input[name=boardContent]").val()
        if (boardTitle === "" || boardContent === ""){
            alert("내용을 입력해주세요");
        }else{
            boardAjax.insertBoard({
                boardTitle,
                boardContent
            }, (result) => {
                if(result.insert === "Ok"){
                    alert("게시글 등록 성공")
                    location.href = "/"
                }else{
                    alert("삭제 실패")
                    location.href = "/"
                }
            })
        }
    })

}
// const selectBoradList = () =>{
//     boardAjax.selectBoardList(drawBoardList)
// }
//
// drawBoardList = (boardList) =>{
//     const boardListJson = JSON.parse(boardList)
//     const boardListArea = document.querySelector(".board-list > .list-group");
//     for(let board of boardListJson){
//         let boardNum = board.boardNum;
//         const boardDetail = document.createElement("a");
//         boardDetail.href = "selectBoardDetail?boardNum="+boardNum
//         boardDetail.classList.add("list-group-item","list-group-item-action");
//         boardDetail.innerText = board.boardTitle
//
//         boardListArea.appendChild(boardDetail)
//     }
// }
//게시판 삭제
const deleteBoard = (boardNum) =>{
    if(window.confirm("삭제하시겠습니까?")){
        const data = {
            boardNum
        }
        boardAjax.deleteBoard(data, (result) =>{
            console.log(result.delete)
            if(result.delete === "Ok"){
                alert("삭제 성공")
                location.href = "/page/bo/list.do"
            }else{
                alert("삭제 실패")
                location.href = "/page/bo/list.do"
            }
        })
    }
}
//게시판 업데이트
const updateBoard = (boardNum) =>{
    const data = {
        boardNum,
        boardTitle : $(".detail-board-title > input").val(),
        boardContent : $(".detail-board-content > textarea").val()
    }
    boardAjax.updateBoard(data, (result) =>{
        if(result.update === "Ok"){
            alert("수정 성공")
            location.href = "/page/bo/list.do"
        }else{
            alert("수정 실패")
            location.href = "/page/bo/list.do"
        }
    })
}