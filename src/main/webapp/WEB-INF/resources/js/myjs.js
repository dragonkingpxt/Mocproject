/**
 * 
 */
   $(document).ready(function()
            {
                // addClass
                $('#addBtn').click(function(){
                    // Thêm class active vào thẻ h1
                	var text1="<div class='col-md-8'>";
					var text2="<form:input path='fileDatas' type='file' cssClass='form-control' />";
				var text3="</div>";
				
                    $('.row').append('<div></div>').text('fffffff');
                });
                 
                // removeClass
                $('#remove').click(function(){
                    // Xóa class active ra khỏi thẻ h1
                    $('this').remove();
                });
            });