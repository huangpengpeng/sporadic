<label><input type="radio"<#rt/>
 value="${rkey}"<#rt/>
<#if (rkey?string=="" && (!value?? || value?string=="")) || (value?? && value?string!="" && value?string==rkey?string)> checked="checked"</#if><#rt/>
<#include "/WEB-INF/ftl/pony/ui/common-attributes.ftl"/><#rt/>
<#include "/WEB-INF/ftl/pony/ui/scripting-events.ftl"/><#rt/>
/><@s.mt code=rvalue text=rvalue/></label><#if hasNext> </#if>