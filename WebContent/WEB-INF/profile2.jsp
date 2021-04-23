<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html style="font-size: 16px;">
  <head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <meta name="keywords" content="Robert Smith">
    <meta name="description" content="">
    <meta name="page_type" content="np-template-header-footer-from-plugin">
    <title>Profile Page</title>
    <link rel="stylesheet" href="assets/css/nicepage.css" media="screen">
<link rel="stylesheet" href="assets/css/Profile.css" media="screen">
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
    <meta property="og:title" content="Profile">
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
            <ul class="u-nav u-unstyled u-nav-1">
            <c:if test="${client.position == 'employee'}">
            	<li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="main.do" style="padding: 10px 20px;">Home</a>	
            </c:if>
            <c:if test="${client.position == 'librarian'}">
            	<li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="main2.do" style="padding: 10px 20px;">Home</a>
            </c:if>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="about.do" style="padding: 10px 20px;">About</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="profile.do" style="padding: 10px 20px;">Profile</a>
</li></ul>
          </div>
          <div class="u-custom-menu u-nav-container-collapse">
            <div class="u-black u-container-style u-inner-container-layout u-opacity u-opacity-95 u-sidenav">
              <div class="u-sidenav-overflow">
                <div class="u-menu-close"></div>
                <ul class="u-align-center u-nav u-popupmenu-items u-unstyled u-nav-2">
                <c:if test="${client.position == 'employee'}">
	            	<li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="main.do" style="padding: 10px 20px;">Home</a>	
	            </c:if>
	            <c:if test="${client.position == 'librarian'}">
	            	<li class="u-nav-item"><a class="u-button-style u-nav-link u-text-active-palette-1-base u-text-hover-palette-2-base" href="main2.do" style="padding: 10px 20px;">Home</a>
	            </c:if>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="about.do" style="padding: 10px 20px;">About</a>
</li><li class="u-nav-item"><a class="u-button-style u-nav-link" href="profile.do" style="padding: 10px 20px;">Profile</a>
</li></ul>
              </div>
            </div>
            <div class="u-black u-menu-overlay u-opacity u-opacity-70"></div>
          </div>
        </nav>
        <h3 class="u-text u-text-1">Welcome, ${client.firstName} ${client.lastName}</h3>
        <c:if test="${client.position == 'employee'}">
	    	<blockquote class="u-text u-text-2">Which periodical you want to read ?</blockquote>
	        <a href="logout.do" class="u-btn u-btn-round u-button-style u-hover-palette-1-light-1 u-palette-1-base u-radius-6 u-btn-1">Logout</a>
	        <form action="search.do" class="u-border-1 u-border-grey-30 u-search u-search-left u-white u-search-1">
	          <button class="u-search-button" type="submit">
	            <span class="u-search-icon u-spacing-10">
	              <svg class="u-svg-link" preserveAspectRatio="xMidYMin slice" viewBox="0 0 56.966 56.966"><use xmlns:xlink="http://www.w3.org/1999/xlink" xlink:href="#svg-df2a"></use></svg>
	              <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" id="svg-df2a" x="0px" y="0px" viewBox="0 0 56.966 56.966" style="enable-background:new 0 0 56.966 56.966;" xml:space="preserve" class="u-svg-content"><path d="M55.146,51.887L41.588,37.786c3.486-4.144,5.396-9.358,5.396-14.786c0-12.682-10.318-23-23-23s-23,10.318-23,23  s10.318,23,23,23c4.761,0,9.298-1.436,13.177-4.162l13.661,14.208c0.571,0.593,1.339,0.92,2.162,0.92  c0.779,0,1.518-0.297,2.079-0.837C56.255,54.982,56.293,53.08,55.146,51.887z M23.984,6c9.374,0,17,7.626,17,17s-7.626,17-17,17  s-17-7.626-17-17S14.61,6,23.984,6z"></path></svg>
	            </span>
	          </button>
	          <input class="u-search-input" type="search" name="search" value="" placeholder="Search">
	        </form>	
	    </c:if>
	    <c:if test="${client.position == 'librarian'}">
	        <blockquote class="u-text u-text-2">Librarian Side</blockquote>
	        <a href="logout.do" class="u-btn u-btn-round u-button-style u-hover-palette-1-light-1 u-palette-1-base u-radius-6 u-btn-1">Logout</a>
	    </c:if>
      </div></header>
    <section class="u-clearfix u-section-1" id="sec-5867">
      <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-clearfix u-expanded-width u-gutter-0 u-layout-wrap u-layout-wrap-1">
          <div class="u-layout">
            <div class="u-layout-row">
              <div class="u-align-center u-container-style u-layout-cell u-left-cell u-size-23 u-layout-cell-1">
                <div class="u-container-layout u-valign-middle u-container-layout-1">
                  <div alt="" class="u-image u-image-circle u-image-1" style="background-image: url('data:image/jpg;base64,${employee.image}')" src="" data-image-width="256" data-image-height="256"></div>
                </div>
              </div>
              <div class="u-align-left u-container-style u-layout-cell u-right-cell u-size-37 u-layout-cell-2">
                <div class="u-container-layout u-container-layout-2">
                  <h2 class="u-text u-text-1">${employee.firstName} ${employee.lastName}</h2>
                  <h6 class="u-text u-text-2">Username: ${employee.userName}</h6>
                  <h6 class="u-text u-text-3">Position: ${employee.position}</h6>
                  <h6 class="u-text u-text-4">Date of birth: ${employee.dateOfBirth}</h6>
                  <h6 class="u-text u-text-5">Mail Code: ${employee.mailCode}</h6>
                  <h6 class="u-text u-text-6">Phone Number: ${employee.phoneNumber}</h6>
                </div>
              </div>
            </div>
          </div>
        </div>
        <h3 class="u-text u-text-7">Periodicals List</h3>
      </div>
    </section>
    <section class="u-align-center u-clearfix u-section-2" id="sec-4f74">
      <div class="u-clearfix u-sheet u-sheet-1">
        <div class="u-expanded-width u-table u-table-responsive u-table-1">
          <table class="u-table-entity u-table-entity-1">
            <colgroup>
              <col width="24.2%">
              <col width="18.5%">
              <col width="18.1%">
              <col width="19.2%">
              <col width="20%">
            </colgroup>
            <thead class="u-palette-1-base u-table-header u-table-header-1">
              <tr style="height: 46px;">
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Periodical Name </th>
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Subscription Date </th>
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Expiration Date </th>
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Received ?</th>
                <th class="u-border-1 u-border-palette-1-base u-table-cell">Sent ?</th>
              </tr>
            </thead>
            <tbody class="u-table-body">
            	<c:forEach var="subscription" items="${subscriptions}">
					<tr style="height: 77px;">
		                <td class="u-border-1 u-border-grey-30 u-first-column u-palette-1-light-3 u-table-cell u-table-cell-6">${subscription.periodicalName}</td>
		                <td class="u-border-1 u-border-grey-30 u-table-cell">${subscription.dateSubscribed}</td>
		                <td class="u-border-1 u-border-grey-30 u-table-cell">${subscription.expirationDate}</td>
		                <c:if test="${subscription.status == 'Not Received'}">
		                	<td class="u-border-1 u-border-grey-30 u-table-cell"><a href="receive.do?periodicalName=${subscription.periodicalName}" style="pointer-events: none;cursor: default;">${subscription.status}</a></td>
		                	<td class="u-border-1 u-border-grey-30 u-table-cell"><a href="sent.do?periodicalName=${subscription.periodicalName}" id="notsenta" onclick="toggle()" style="color:red;pointer-events: none;cursor: default;">Not Sent</a></td>
		                </c:if>
		                <c:if test="${subscription.status == 'Received'}">
		                	<td class="u-border-1 u-border-grey-30 u-table-cell"><a href="receive.do?periodicalName=${subscription.periodicalName}" style="color:green;pointer-events: none;cursor: default;">${subscription.status}</a></td>
		                	<td class="u-border-1 u-border-grey-30 u-table-cell"><a href="sent.do?periodicalName=${subscription.periodicalName}" style="pointer-events: none;cursor: default;" id="notsenta" onclick="toggle()">Not Sent</a></td>
		                </c:if>
		                <c:if test="${subscription.status == 'Waiting'}">
		                	<td class="u-border-1 u-border-grey-30 u-table-cell"><a href="receive.do?periodicalName=${subscription.periodicalName}" style="color:red;pointer-events: none;cursor: default;">${subscription.status}</a></td>
		                	<td class="u-border-1 u-border-grey-30 u-table-cell"><a href="sent.do?periodicalName=${subscription.periodicalName}" id="notsenta" onclick="toggle()" style="color:red;pointer-events: none;cursor: default;">Waiting</a></td>
		                </c:if>
		              </tr>
				</c:forEach>
				<script>
					function toggle() {
						document.getElementById("notsenta").style.color = "green";
					}
				</script>
            </tbody>
          </table>
        </div>
      </div>
    </section>
    
    
    <footer class="u-align-center u-clearfix u-footer u-grey-80 u-footer" id="sec-65ad"><div class="u-clearfix u-sheet u-sheet-1">
        <p class="u-small-text u-text u-text-variant u-text-1">©2021 Happy Life Insurance Company</p>
      </div></footer>
  </body>
</html>