const boardAjax = {
    insertBoard  : (data,callback) => {
        $.ajax({
            url: "/api/bo/insert.do",
            type: "post",
            data,
            dataType: "json",
            success: (result) => {
                callback(result);
            },
            error: (err) => {
                console.log(err)
            }
        })
    },
    //
    // selectBoardList : (callback) =>{
    //     $.ajax({
    //         url: "selectBoardList",
    //         type: "post",
    //         dataType: "text",
    //         success: (result) => {
    //             callback(result);
    //         },
    //         error: (err) => {
    //             console.log(err)
    //         }
    //     })
    // },
    deleteBoard : (data,callback) =>{
        $.ajax({
            url: "/api/bo/delete.do",
            type: "post",
            data,
            dataType: "json",
            success: (result) => {
                console.log(result)
                callback(result);
            },
            error: (err) => {
                console.log(err)
            }
        })
    },
    updateBoard : (data,callback) =>{

    }
}
