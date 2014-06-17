<#macro paginable data=""  form="form" class="pagination" selectClass="active">
          <ul class="${class}">
                <#if !data.firstPage>
               <li><a href="javascript:p_go(${data.pageNo-1})">上一页</a> </li>
                </#if>
                <#assign s=1 e=6 />
                <#if data.totalPage gt 6>
	                <#if data.pageNo lte 3>
	                	<#assign s=1 e=6 />
	                	<#else>
	                	<#assign s=data.pageNo-3 />
	                </#if>
	               	<#if data.pageNo gt data.totalPage-2>
	               		<#assign s=data.totalPage-5 e=data.totalPage />
	               		<#else>
	               		<#if data.pageNo lte 3>
	               		<#assign e=6 >
	               		<#else>
	               		<#assign e=data.pageNo+2 />
	               		</#if>
	               	</#if>
	            <#else>
	            <#assign e=data.totalPage />   	
               	</#if>
               	<#if data.totalPage gt 1>
	             	<#list s..e as c>
	             		<#if  data.totalPage gte c >
	             	 		<li><a href="javascript:p_go(${c})" <#if data.pageNo==c>class="${selectClass}"</#if> > ${c}</a></li>
	             	 	</#if>
	             	</#list>
             	</#if>
                <#if !data.lastPage> 
                <li><a href="javascript:p_go(${data.pageNo+1})">下一页</a></li>
                </#if> 
                 <li><a href="#">总数${(data.totalCount)!}</a></li>
       </ul>
	 <script>
		function p_go(pageNo){
		 	seajs.use('jquery', function ($) {
		 		if($("#${form} > *[name='pageNo']").length==0){
		 			var h=$("<input name='pageNo' type='hidden'></input>");
		 			$(h).val(pageNo);
		 			h.appendTo($("#${form}"));
		 		}
			 	$("#${form} > *[name='pageNo']").val(pageNo);
			 	$("#${form}").submit();
		 	})
	 	}
	 </script>
</#macro>
