<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<div class="control-group">
    <label class="control-label" >What did you do?</label>

    <div class="controls">
        <form:textarea path="what"/>
        <form:errors path="what" class="text-error" element="div" />
        <p class="help-block">Describe shortly which task did you accomplish.</p>
    </div>
</div>
<div class="control-group">
    <label class="control-label">When</label>

    <div class="controls">
        <form:input path="whenDate" />
        <form:errors path="whenDate" class="text-error" element="div"/>
        <p class="help-block">When did you do it?</p>
    </div>
</div>
<div class="control-group">
    <label class="control-label" >How difficult was it?</label>

    <div class="controls">
        <form:select path="difficulty" >
            <form:option value="EASY"/>
            <form:option value="MEDIUM"/>
            <form:option value="HARD"/>
        </form:select>
    </div>
</div>
<form:hidden path="id"  />
<form:hidden path="ownedBy"  />