
$('#graphListForm\\:addNewGraphMenuitem').on('click', function (e) {
    $('#graphListForm\\:addNewGraphCommandButtonHidden').click();
});

$('#graphListForm\\:editSelectedGraphMenuitem').on('click', function (e) {
    e.preventDefault();
    e.stopPropagation();
    $('#graphListForm\\:editSelectedGraphCommandButtonHidden').click();
});

$('#graphListForm\\:deleteSelectedGraphMenuitem').on('click', function (e) {
    $('#graphListForm\\:deleteSelectedGraphCommandButtonidden').click();
});



