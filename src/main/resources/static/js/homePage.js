$(() => {
  const menu = $('#menu-ctn'),
bars = $('.menu-bars'),
items = $('.menu-item'),
content = $('#menu-cnt');

  let firstClick = true,
menuClosed = true;

  let handleMenu = event => {
    if(!firstClick) {
      bars.toggleClass('crossed hamburger');
    } else {
      bars.addClass('crossed');
      firstClick = false;
    }

    menuClosed = !menuClosed;
    content.toggleClass('dropped');
    event.stopPropagation();
  };

  menu.on('click', event => {
    handleMenu(event);
  });

  $('body').not('#menu-cnt, #menu-ctn').on('click', event => {
    if(!menuClosed) handleMenu(event);
  });

  $('#menu-cnt, #menu-ctn').on('click', event => event.stopPropagation());
});