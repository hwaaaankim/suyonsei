$(function(){
	if(!searchType || searchType === 'name' || searchType === 'phone' || searchType == 'none' || searchType == 'email'){
		$('#periodSearch').hide();
		$('#businessSearch').hide();
		$('#subjectSearch').hide();
		$('#textSearch').show();
	}else if(searchType==='period'){
		$('#businessSearch').hide();
		$('#subjectSearch').hide();
		$('#textSearch').hide();
		$('#periodSearch').show();
	}else if(searchType === 'subject'){
		$('#businessSearch').hide();
		$('#subjectSearch').show();
		$('#textSearch').hide();
		$('#periodSearch').hide();
	}
	
	$('#searchType').on('change',function(){
		if($('#searchType option:selected').attr('id')==='searchName' || 
		$('#searchType option:selected').attr('id') === 'searchPhone' ||
		$('#searchType option:selected').attr('id') === 'searchEmail' ||
		$('#searchType option:selected').attr('id') === 'searchBasic'){
			$('#periodSearch').hide();
			$('#textSearch').show();
			$('#subjectSearch').hide();
			$('#textSearch input').val('');
		}else if($('#searchType option:selected').attr('id')==='searchPeriod'){
			$('#textSearch').hide();
			$('#subjectSearch').hide();
			$('#periodSearch').show();
			$('#startDate').val(new Date().toISOString().slice(0, 10));
			$('#endDate').val(new Date().toISOString().slice(0, 10));
		}else if($('#searchType option:selected').attr('id') === 'searchSubject'){
			$('#businessSearch').hide();
			$('#subjectSearch').show();
			$('#textSearch').hide();
			$('#periodSearch').hide();
		}
	});
	
});