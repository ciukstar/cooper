
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

$('#graphDefinitionForm\\:graphDefinitionTabView\\:addNewGraphNodeMenuitem').click(function(e) {
   $('#graphDefinitionForm\\:graphDefinitionTabView\\:addNewGraphNodeCommandButtonHidden').click();
});
$('#graphDefinitionForm\\:graphDefinitionTabView\\:deleteSelectedGraphNodeMenuitem').click(function(e) {
   $('#graphDefinitionForm\\:graphDefinitionTabView\\:deleteSelectedGraphNodeCommandButtonHidden').click();
});



