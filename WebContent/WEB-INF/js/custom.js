$(".plus").on( "click", function() {
    if($(".plus").hasClass("active")){
    $('.bloc_comment').css('display','none');
    $('.plus').val('+');
     $(".plus").removeClass("active")
    }
    else{
    $(".plus").addClass("active")
    $('.bloc_comment').css('display','block');
    $('.plus').val('-');
    }
});