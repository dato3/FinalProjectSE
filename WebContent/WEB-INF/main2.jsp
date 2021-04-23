<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="font-size: 16px;">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="page_type" content="np-template-header-footer-from-plugin">
    <title>Home Page</title>
    <link rel="stylesheet" href="assets/css/nicepage.css" media="screen">
<link rel="stylesheet" href="assets/css/Home2.css" media="screen">
    <script class="u-script" type="text/javascript" src="assets/js/jquery.js" defer=""></script>
    <script class="u-script" type="text/javascript" src="assets/js/nicepage.js" defer=""></script>
    <meta name="generator" content="Nicepage 3.12.1, nicepage.com">
    <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,600,600i,700,700i,800,800i">
    
    
    
    <script type="application/ld+json">{
    "@context": "http://schema.org",
    "@type": "Organization",
    "name": "",
    "url": "index.html"
}</script>
    <meta property="og:title" content="Librarian Page">
    <meta property="og:type" content="website">
    <meta name="theme-color" content="#478ac9">
    <link rel="canonical" href="index.html">
    <meta property="og:url" content="index.html">
  </head>
  <body class="u-body"><header class="u-clearfix u-header u-white u-header" id="sec-1043"><div class="u-clearfix u-sheet u-sheet-1">
        <nav class="u-menu u-menu-dropdown u-offcanvas u-menu-1">
          <div class="menu-collapse" style="font-size: 1rem; letter-spacing: 0px;">
            <a class="u-button-style u-custom-left-right-menu-spacing u-custom-padding-bottom u-custom-top-bottom-menu-spacing u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="#">
              <svg><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#menu-hamburger"></use></svg>
              <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink"><defs><symbol id="menu-hamburger" viewBox="0 0 16 16" style="width: 16px; height: 16px;"><rect y="1" width="16" height="2"></rect><rect y="7" width="16" height="2"></rect><rect y="13" width="16" height="2"></rect>
</symbol>
</defs></svg>
            </a>
          </div>
          <div class="u-custom-menu u-nav-container">
            <ul class="u-nav u-unstyled u-nav-1"><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="main2.do" style="padding: 10px 20px;">Home</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="about.do" style="padding: 10px 20px;">About</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="profile.do" style="padding: 10px 20px;">Profile</a>
</li></ul>
          </div>
          <div class="u-custom-menu u-nav-container-collapse">
            <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
              <div class="u-sidenav-overflow">
                <div class="u-menu-close"></div>
                <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2"><li class="u-nav-item"><a class="u-button-style u-nav-link" href="main2.do" style="padding: 10px 20px;">Home</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="about.do" style="padding: 10px 20px;">About</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="profile.do" style="padding: 10px 20px;">Profile</a>
</li></ul>
              </div>
            </div>
            <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
          </div>
        </nav>
        <h3 class="u-text u-text-1">Welcome, ${client.firstName} ${client.lastName}</h3>
        <blockquote class="u-text u-text-2">Librarian Side</blockquote>
        <a href="logout.do" class="u-btn u-btn-round u-button-style u-hover-palette-1-light-1 u-palette-1-base u-radius-6 u-btn-1">Logout</a>
        <!-- <form action="#" class="u-border-1 u-border-grey-30 u-search u-search-left u-white u-search-1">
          <button class="u-search-button" type="submit">
            <span class="u-search-icon u-spacing-10">
              <svg class="u-svg-link" preserveAspectRatio="xMidYMin slice" viewBox="0 0 56.966 56.966"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg-df2a"></use></svg>
              <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="svg-df2a" x="0px" y="0px" viewBox="0 0 56.966 56.966" style="enable-background:new 0 0 56.966 56.966;" xml:space="preserve" class="u-svg-content"><path d="M55.146,51.887L41.588,37.786c3.486-4.144,5.396-9.358,5.396-14.786c0-12.682-10.318-23-23-23s-23,10.318-23,23  s10.318,23,23,23c4.761,0,9.298-1.436,13.177-4.162l13.661,14.208c0.571,0.593,1.339,0.92,2.162,0.92  c0.779,0,1.518-0.297,2.079-0.837C56.255,54.982,56.293,53.08,55.146,51.887z M23.984,6c9.374,0,17,7.626,17,17s-7.626,17-17,17  s-17-7.626-17-17S14.61,6,23.984,6z"></path></svg>
            </span>
          </button>
          <input class="u-search-input" type="search" name="search" value="" placeholder="Search">
        </form> -->
      </div></header>
    <section class="u-align-center u-clearfix u-section-1" id="sec-6d61">
      <div class="u-clearfix u-sheet u-valign-bottom u-sheet-1">
        <div class="u-expanded-width u-table u-table-responsive u-table-1">
          <table class="u-table-entity u-table-entity-1">
            <colgroup>
              <col width="20%">
              <col width="10%">
              <col width="10%">
              <col width="10%">
              <col width="10%">
              <col width="10%">
              <col width="10%">
              <col width="10%">
              <col width="10%">
            </colgroup>
            <thead class="u-palette-1-base u-table-header u-table-header-1">
              <tr style="height: 45px;">
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Periodical Name</th>
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Current Reader</th>
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Status</th>
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Expiration Date</th>
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Next Reader</th>
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Status</th>
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Expiration Date</th>
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Change Availability</th>
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Delete Periodical</th>
              </tr>
            </thead>
            <tbody class="u-table-body">
            	<c:forEach var="listitem" items="${librarianList}">
					<tr style="height: 79px;">
		                <td class="u-border-1 u-border-grey-30 u-first-column u-palette-1-light-3 u-table-cell u-table-cell-7">${listitem.periodicalName}</td>
		                <c:if test="${listitem.currentReader != 'None'}">
		                	<td class="u-border-1 u-border-grey-30 u-table-cell"><a href="profile.do?fullName=${listitem.currentReader}">${listitem.currentReader}</a></td>
		                </c:if>
		                <c:if test="${listitem.currentReader == 'None'}">
		                	<td class="u-border-1 u-border-grey-30 u-table-cell">${listitem.currentReader}</td>
		                </c:if>
		                <td class="u-border-1 u-border-grey-30 u-table-cell">${listitem.statusCur}</td>
		                <td class="u-border-1 u-border-grey-30 u-table-cell">${listitem.expirationDateCurrent}</td>
		                <c:if test="${listitem.nextReader != 'None'}">
		                	<td class="u-border-1 u-border-grey-30 u-table-cell"><a href="profile.do?fullName=${listitem.nextReader}">${listitem.nextReader}</a></td>
		                </c:if>
		                <c:if test="${listitem.nextReader == 'None'}">
		                	<td class="u-border-1 u-border-grey-30 u-table-cell">${listitem.nextReader}</td>
		                </c:if>
		                <td class="u-border-1 u-border-grey-30 u-table-cell">${listitem.statusNext}</td>
		                <td class="u-border-1 u-border-grey-30 u-table-cell">${listitem.expirationDateNext}</td>
		                <td class="u-border-1 u-border-grey-30 u-table-cell"><a href="changeAvailability.do?periodicalName=${listitem.periodicalName}&changeType=${listitem.availability}">${listitem.availability}</a></td>
		                <c:if test="${listitem.currentReader != 'None'}">
		                	<td class="u-border-1 u-border-grey-30 u-table-cell"><a href="#" style="color:red;pointer-events: none;cursor: default;">Delete</a></td>
		                </c:if>
		                <c:if test="${listitem.currentReader == 'None'}">
		                	<td class="u-border-1 u-border-grey-30 u-table-cell"><a href="deletePeriodical.do?periodicalName=${listitem.periodicalName}">Delete</a></td>
		                </c:if>
		              </tr>
				</c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </section>
    <section class="u-align-center u-clearfix u-section-2" id="sec-34df">
      <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-form u-form-1">
          <form action="periodicalInsert.do" method="POST" enctype="multipart/form-data" class="u-clearfix u-form-spacing-15 u-inner-form" style="padding: 15px;" source="custom" name="form">
          	<div class="u-form-group u-form-name u-form-group-1">
              <label for="name-6797" class="u-form-control-hidden u-label">Periodical Name</label>
              <input type="text" placeholder="Periodical Name" id="name-6797" name="periodicalName" class="u-border-1 u-border-grey-30 u-input u-input-rectangle" required="">
            </div>
            <div class="u-form-group u-form-message u-form-group-2">
              <label for="message-6797" class="u-form-control-hidden u-label">Title</label>
              <textarea placeholder="Title" rows="4" cols="50" id="message-6797" name="periodicalTitle" class="u-border-1 u-border-grey-30 u-input u-input-rectangle" required=""></textarea>
            </div>
            <div class="u-align-left u-form-group u-form-submit u-form-group-3">
            	<input type="file" name="image"/>
            	<input type="submit" class="u-btn u-btn-submit u-button-style" value="Add Periodical">
            </div>
            <div class="u-form-send-message u-form-send-success">Periodical has been added.</div>
          </form>
        </div>
      </div>
    </section>
    
    
    <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-65ad"><div class="u-clearfix u-sheet u-sheet-1">
        <p class="u-small-text u-text u-text-variant u-text-1">©2021 Happy Life Insurance Company</p>
      </div></footer>
  </body>
</html>