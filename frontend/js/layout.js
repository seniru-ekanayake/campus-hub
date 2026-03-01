// layout.js – injects shared navbar + sidebar
// Include AFTER app.js on authenticated pages

function renderLayout(activeSection = '') {
  const user = Auth.getUser();
  const isAdmin = Auth.isAdmin();

  const navbarHtml = `
  <nav class="navbar">
    <div class="container">
      <a href="dashboard.html" class="navbar-brand">
        <div class="logo-circle">UW</div>
        <span>Campus Hub &bull; University of Wolverhampton</span>
      </a>
      <ul class="navbar-nav" id="main-nav">
        <li><a href="dashboard.html">Home</a></li>
        <li><a href="facilities.html">Facilities</a></li>
        <li><a href="events.html">Events</a></li>
        <li><a href="clubs.html">Clubs</a></li>
        <li><a href="transport.html">Transport</a></li>
        <li><a href="checkin.html">Check-In</a></li>
        <li><a href="mental-health.html">Wellbeing</a></li>
        ${isAdmin ? `<li><a href="admin.html" style="color:var(--gold)">⚙ Admin</a></li>` : ''}
      </ul>
      <div class="navbar-actions">
        <span class="nav-user" id="nav-user"></span>
        <a href="profile.html" class="btn btn-outline btn-sm">Profile</a>
        <button id="btn-logout" class="btn btn-sm" style="background:rgba(255,255,255,0.15);color:#fff">Logout</button>
      </div>
    </div>
  </nav>`;

  const sidebarHtml = `
  <aside class="sidebar">
    <div class="sidebar-section">
      <div class="sidebar-label">Navigation</div>
      <a href="dashboard.html" ${activeSection==='dashboard'?'class="active"':''}>
        <span class="sidebar-icon">🏠</span> Dashboard
      </a>
      <a href="announcements.html" ${activeSection==='announcements'?'class="active"':''}>
        <span class="sidebar-icon">📢</span> Announcements
      </a>
      <a href="events.html" ${activeSection==='events'?'class="active"':''}>
        <span class="sidebar-icon">📅</span> Events
      </a>
    </div>
    <div class="sidebar-section">
      <div class="sidebar-label">Campus</div>
      <a href="facilities.html" ${activeSection==='facilities'?'class="active"':''}>
        <span class="sidebar-icon">🏛</span> Facilities
      </a>
      <a href="transport.html" ${activeSection==='transport'?'class="active"':''}>
        <span class="sidebar-icon">🚌</span> Transport
      </a>
    </div>
    <div class="sidebar-section">
      <div class="sidebar-label">Student Life</div>
      <a href="clubs.html" ${activeSection==='clubs'?'class="active"':''}>
        <span class="sidebar-icon">🎓</span> Club Hub
      </a>
      <a href="checkin.html" ${activeSection==='checkin'?'class="active"':''}>
        <span class="sidebar-icon">⭐</span> Check-In & Rewards
      </a>
      <a href="mental-health.html" ${activeSection==='mental-health'?'class="active"':''}>
        <span class="sidebar-icon">💚</span> Wellbeing
      </a>
    </div>
    <div class="sidebar-section">
      <div class="sidebar-label">Account</div>
      <a href="profile.html" ${activeSection==='profile'?'class="active"':''}>
        <span class="sidebar-icon">👤</span> My Profile
      </a>
      ${isAdmin ? `
      <a href="admin.html" ${activeSection==='admin'?'class="active"':''} style="color:var(--gold-dark)">
        <span class="sidebar-icon">⚙</span> Admin Panel
      </a>` : ''}
      <a href="#" id="btn-logout-sidebar">
        <span class="sidebar-icon">🚪</span> Logout
      </a>
    </div>
  </aside>`;

  // Insert navbar before main or body
  document.body.insertAdjacentHTML('afterbegin', navbarHtml);

  // Wrap existing content in layout
  const existingContent = document.querySelector('.page-content') || document.querySelector('main');
  if (!existingContent) return;

  const wrapper = document.createElement('div');
  wrapper.className = 'main-layout';
  wrapper.innerHTML = sidebarHtml;
  const contentDiv = document.createElement('div');
  contentDiv.className = 'main-content';

  existingContent.parentNode.insertBefore(wrapper, existingContent);
  wrapper.appendChild(contentDiv);
  contentDiv.appendChild(existingContent);

  renderNavUser();

  document.getElementById('btn-logout')?.addEventListener('click', () => Auth.logout());
  document.getElementById('btn-logout-sidebar')?.addEventListener('click', (e) => { e.preventDefault(); Auth.logout(); });
}
