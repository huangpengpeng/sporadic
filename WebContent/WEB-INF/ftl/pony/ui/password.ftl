<#--
<input type="password"/>
-->
<#macro password
	maxlength="" readonly="" value="" autocomplete="off"
	label="" noHeight="false" required="false" colspan="" width="100" help="" helpPosition="2" colon=":" hasColon="true"
	id="" name="" class="" style="" size="" title="" disabled="" tabindex="" accesskey=""
	vld="" equalTo="" maxlength="" minlength=""
	onclick="" ondblclick="" onmousedown="" onmouseup="" onmouseover="" onmousemove="" onmouseout="" onfocus="" onblur="" onkeypress="" onkeydown="" onkeyup="" onselect="" onchange=""
	>
<#include "/WEB-INF/ftl/pony/ui/control.ftl"/><#rt/>
<input type="password" autocomplete="${autocomplete}"<#rt/>
<#if id!=""> id="${id}"</#if><#rt/>
<#if maxlength!=""> maxlength="${maxlength}"</#if><#rt/>
<#if readonly!=""> readonly="${readonly}"</#if><#rt/>
<#if value!=""> value="${value}"</#if><#rt/>
<#include "/WEB-INF/ftl/pony/ui/common-attributes.ftl"/><#rt/>
<#include "/WEB-INF/ftl/pony/ui/scripting-events.ftl"/><#rt/>
/>
<#include "/WEB-INF/ftl/pony/ui/control-close.ftl"/><#rt/>
</#macro>