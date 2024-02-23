	$('#select-wrap').css('display','none');
$(function(){
	$('#wear-wrap .select-items').on('click', function(){
		var value = $('#wear').val();
		if( value.match('렌즈 착용중')){
			$('#sort').attr('required', true);
			$('#select-wrap').css('display','block');
		}else{
			$('#select-wrap').css('display','none');
			$('#sort').attr('required', false);
		}
	});

});