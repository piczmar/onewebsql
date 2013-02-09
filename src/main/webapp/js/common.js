function go(url) {
    window.location = url;
}

function deleteItem(url) {
    var isOK = confirm("Are you sure to delete?");
    if (isOK) {
        go(url);
    }
}

$(function () {
    var pickerOpts = {
        dateFormat:"yy-mm-dd",
        defaultDate: new Date()
    };
    $("#whenDate").datepicker(pickerOpts);
    if($("#whenDate").datepicker("getDate")==null)
        $("#whenDate").datepicker("setDate", new Date());
});