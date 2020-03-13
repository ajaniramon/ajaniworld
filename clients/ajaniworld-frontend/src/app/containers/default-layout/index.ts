export * from './default-layout.component';

$('body').on('click', '.dropdown-toggle', function(){
  // fix for core ui 2 dropdowns
  if($(this).next('.dropdown-menu').css('display') === 'none'){
    $(this).next('.dropdown-menu').show();
    $(this).blur(function(){ $(this).next('.dropdown-menu').hide(); });
  }else{
    $(this).next('.dropdown-menu').hide();
  }
});
