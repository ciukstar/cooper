
$('#statusListForm\\:addNewStatusMenuitem').on('click', function (e) {
    $('#statusListForm\\:addNewStatusCommandButtonHidden').click();
});

$('#statusListForm\\:editSelectedStatusMenuitem').on('click', function (e) {
    e.preventDefault();
    e.stopPropagation();
    $('#statusListForm\\:editSelectedStatusCommandButtonHidden').click();
});

$('#statusListForm\\:deleteSelectedStatusMenuitem').on('click', function (e) {
    $('#statusListForm\\:deleteSelectedStatusCommandButtonHidden').click();
});

