$(function(){
		$("#sendForm").submit(function(){
			$("label[for='inputError']").remove();
			checkFrom($("#form"));
			checkPassword($("#password"));
			checkTo($("#to"));
			checkSubject($("#subject"));
			checkText($("#text"));
			if($("label[for='inputError']").length>0){
				return false;
			}
			$("#submit").button('loading');
			return true;
		});
		$("#form,#to").mailAutoComplete();
		$("#form,#password,#to,#subject,#text").blur(function(){
			if($(this).attr("id")=="form"){
				return checkFrom(this);
			}
			if($(this).attr("id")=="password"){
				return checkPassword(this);
			}
			if($(this).attr("id")=="to"){
				return checkTo(this);
			}
			if($(this).attr("id")=="subject"){
				return checkSubject(this);
			}
			if($(this).attr("id")=="text"){
				return checkText(this);
			}
		});
		$("#form,#password,#to,#subject,#text").focus(function(){
			$(this).parents(".form-group").removeClass("has-error");
			$(this).parents(".form-group").children("label[for='inputError']").remove();
		});
		function checkFrom(obj){
			var val=$(obj).val();
			if(!chackNull(obj)){
				return false;
			}
			var Regex = /^(?:\w+\.?)*\w+@(?:\w+\.)*\w+$/;  
			if(!Regex.test(val)){
				$(obj).parents(".form-group").addClass("has-error");
				$(obj).parents(".form-group").children("label").after('<label class="control-label" for="inputError">格式错误</label>');
				return false;
			}
			return true;
		}
		function checkPassword(obj){
			if(!chackNull(obj)){
				return false;
			}
			return true;
		}
		function checkTo(obj){
			var val=$(obj).val();
			if(!chackNull(obj)){
				return false;
			}
			var Regex = /^(?:\w+\.?)*\w+@(?:\w+\.)*\w+$/;  
			if(!Regex.test(val)){
				$(obj).parents(".form-group").addClass("has-error");
				$(obj).parents(".form-group").children("label").after('<label class="control-label" for="inputError">格式错误</label>');
				return false;
			}
			return true;
		}
		function checkSubject(obj){
			var val=$(obj).val();
			if(!chackNull(obj)){
				return false;
			}
			if(val.length>120){
				$(obj).parents(".form-group").addClass("has-error");
				$(obj).parents(".form-group").children("label").after('<label class="control-label" for="inputError">主题长度不能超过120个中文字符</label>');
				return false;
			}
		}
		function checkText(obj){
			var val=$(obj).val();
			if(!chackNull(obj)){
				return false;
			}
			if(val.length>120){
				$(obj).parents(".form-group").addClass("has-error");
				$(obj).parents(".form-group").children("label").after('<label class="control-label" for="inputError">正文长度不能超5000个中文字符</label>');
				return false;
			}
		}
		function chackNull(obj){
			var val=$(obj).val();
			if(val==null||val.length==0){
				$(obj).parents(".form-group").addClass("has-error");
				$(obj).parents(".form-group").children("label").after('<label class="control-label" for="inputError">不能为空</label>');
				return false;
			}
			return true;
		}
	});