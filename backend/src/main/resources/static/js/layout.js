// layout.js – Campus Hub · UoW Design System v3 · Industry Grade

function renderLayout(activeSection = '') {
  const user = Auth.getUser();
  const isAdmin = Auth.isAdmin();
  const displayName = user ? (user.firstName || user.username || 'Student') : 'Student';
  const initials = displayName.charAt(0).toUpperCase();
  const role = isAdmin ? 'Administrator' : (user?.course || 'Student');

  // ── SVG ICON LIBRARY ──────────────────────────────────────────
  const icons = {
    dashboard:    `<svg class="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/><rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/></svg>`,
    events:       `<svg class="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><rect x="3" y="4" width="18" height="18" rx="2"/><line x1="16" y1="2" x2="16" y2="6"/><line x1="8" y1="2" x2="8" y2="6"/><line x1="3" y1="10" x2="21" y2="10"/></svg>`,
    clubs:        `<svg class="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"/><circle cx="9" cy="7" r="4"/><path d="M23 21v-2a4 4 0 0 0-3-3.87"/><path d="M16 3.13a4 4 0 0 1 0 7.75"/></svg>`,
    announcements:`<svg class="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/></svg>`,
    facilities:   `<svg class="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/><polyline points="9,22 9,12 15,12 15,22"/></svg>`,
    transport:    `<svg class="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><rect x="1" y="3" width="15" height="13" rx="2"/><path d="M16 8h4l3 3v5h-7V8z"/><circle cx="5.5" cy="18.5" r="2.5"/><circle cx="18.5" cy="18.5" r="2.5"/></svg>`,
    checkin:      `<svg class="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><polyline points="9,11 12,14 22,4"/><path d="M21 12v7a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11"/></svg>`,
    wellbeing:    `<svg class="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"/></svg>`,
    profile:      `<svg class="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>`,
    admin:        `<svg class="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><circle cx="12" cy="12" r="3"/><path d="M19.07 4.93a10 10 0 0 1 0 14.14M4.93 4.93a10 10 0 0 0 0 14.14"/><path d="M16.24 7.76a6 6 0 0 1 0 8.49M7.76 7.76a6 6 0 0 0 0 8.49"/></svg>`,
    logout:       `<svg class="nav-icon" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/><polyline points="16,17 21,12 16,7"/><line x1="21" y1="12" x2="9" y2="12"/></svg>`,
    search:       `<svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="search-icon" aria-hidden="true"><circle cx="11" cy="11" r="8"/><line x1="21" y1="21" x2="16.65" y2="16.65"/></svg>`,
    bell:         `<svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M18 8A6 6 0 0 0 6 8c0 7-3 9-3 9h18s-3-2-3-9"/><path d="M13.73 21a2 2 0 0 1-3.46 0"/></svg>`,
    signout:      `<svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4"/><polyline points="16,17 21,12 16,7"/><line x1="21" y1="12" x2="9" y2="12"/></svg>`,
    hamburger:    `<svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><line x1="3" y1="6" x2="21" y2="6"/><line x1="3" y1="12" x2="21" y2="12"/><line x1="3" y1="18" x2="21" y2="18"/></svg>`,
    chevronDown:  `<svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><polyline points="6,9 12,15 18,9"/></svg>`,
    home:         `<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"/><polyline points="9,22 9,12 15,12 15,22"/></svg>`,
    grid:         `<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><rect x="3" y="3" width="7" height="7" rx="1"/><rect x="14" y="3" width="7" height="7" rx="1"/><rect x="3" y="14" width="7" height="7" rx="1"/><rect x="14" y="14" width="7" height="7" rx="1"/></svg>`,
    calendar:     `<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><rect x="3" y="4" width="18" height="18" rx="2"/><line x1="16" y1="2" x2="16" y2="6"/><line x1="8" y1="2" x2="8" y2="6"/><line x1="3" y1="10" x2="21" y2="10"/></svg>`,
    heart:        `<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M20.84 4.61a5.5 5.5 0 0 0-7.78 0L12 5.67l-1.06-1.06a5.5 5.5 0 0 0-7.78 7.78l1.06 1.06L12 21.23l7.78-7.78 1.06-1.06a5.5 5.5 0 0 0 0-7.78z"/></svg>`,
    user:         `<svg viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" aria-hidden="true"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"/><circle cx="12" cy="7" r="4"/></svg>`,
  };

  // Background mesh
  const bgMesh = document.createElement('div');
  bgMesh.className = 'bg-mesh';
  bgMesh.setAttribute('aria-hidden', 'true');
  document.body.insertAdjacentElement('afterbegin', bgMesh);

  // Sidebar overlay (mobile)
  const overlayEl = document.createElement('div');
  overlayEl.className = 'sidebar-overlay';
  overlayEl.id = 'sidebar-overlay';
  overlayEl.setAttribute('aria-hidden', 'true');
  document.body.appendChild(overlayEl);

  // ── SIDEBAR HTML ─────────────────────────────────────────────
  const sidebarHtml = `
<nav class="sidebar" id="main-sidebar" aria-label="Main navigation">
  <a href="dashboard.html" class="sidebar-brand" aria-label="Campus Hub home">
    <svg class="brand-bookmark" viewBox="0 0 36 44" fill="none" aria-hidden="true">
      <path d="M0 0 H36 V38 L18 28 L0 38 Z" fill="#4D5168"/>
      <polygon points="18,4 30,16 18,16" fill="#A8D846" opacity="0.9"/>
      <polygon points="18,4 6,16 18,16" fill="#F5357F" opacity="0.9"/>
      <polygon points="18,16 30,16 18,28" fill="#FAC817" opacity="0.9"/>
      <polygon points="18,16 6,16 18,28" fill="#4DBDD7" opacity="0.9"/>
    </svg>
    <div class="brand-text">
      <span>University of</span>
      <strong>Wolverhampton</strong>
    </div>
  </a>

  <span class="nav-section-label" aria-hidden="true">Overview</span>
  <ul role="list" aria-label="Overview">
    <li class="nav-item">
      <a href="dashboard.html" class="${activeSection==='dashboard'?'active':''}" aria-current="${activeSection==='dashboard'?'page':'false'}">
        ${icons.dashboard}<span>Dashboard</span>
      </a>
    </li>
    <li class="nav-item">
      <a href="events.html" class="${activeSection==='events'?'active':''}" aria-current="${activeSection==='events'?'page':'false'}">
        ${icons.events}<span>Events</span>
      </a>
    </li>
    <li class="nav-item">
      <a href="clubs.html" class="${activeSection==='clubs'?'active':''}" aria-current="${activeSection==='clubs'?'page':'false'}">
        ${icons.clubs}<span>Club Hub</span>
      </a>
    </li>
    <li class="nav-item">
      <a href="announcements.html" class="${activeSection==='announcements'?'active':''}" aria-current="${activeSection==='announcements'?'page':'false'}">
        ${icons.announcements}<span>Announcements</span>
      </a>
    </li>
  </ul>

  <span class="nav-section-label" aria-hidden="true">Campus</span>
  <ul role="list" aria-label="Campus">
    <li class="nav-item">
      <a href="facilities.html" class="${activeSection==='facilities'?'active':''}" aria-current="${activeSection==='facilities'?'page':'false'}">
        ${icons.facilities}<span>Facilities</span>
      </a>
    </li>
    <li class="nav-item">
      <a href="transport.html" class="${activeSection==='transport'?'active':''}" aria-current="${activeSection==='transport'?'page':'false'}">
        ${icons.transport}<span>Transport</span>
      </a>
    </li>
    <li class="nav-item">
      <a href="checkin.html" class="${activeSection==='checkin'?'active':''}" aria-current="${activeSection==='checkin'?'page':'false'}">
        ${icons.checkin}<span>Check-In &amp; Rewards</span>
      </a>
    </li>
    <li class="nav-item">
      <a href="mental-health.html" class="${activeSection==='mental-health'?'active':''}" aria-current="${activeSection==='mental-health'?'page':'false'}">
        ${icons.wellbeing}<span>Wellbeing</span>
      </a>
    </li>
  </ul>

  <span class="nav-section-label" aria-hidden="true">Account</span>
  <ul role="list" aria-label="Account">
    <li class="nav-item">
      <a href="profile.html" class="${activeSection==='profile'?'active':''}" aria-current="${activeSection==='profile'?'page':'false'}">
        ${icons.profile}<span>My Profile</span>
      </a>
    </li>
    ${isAdmin ? `
    <li class="nav-item">
      <a href="admin.html" class="${activeSection==='admin'?'active':''}" style="color:rgba(250,200,23,0.75)" aria-current="${activeSection==='admin'?'page':'false'}">
        ${icons.admin}<span>Admin Panel</span>
      </a>
    </li>` : ''}
    <li class="nav-item">
      <a href="#" id="btn-logout-sidebar" aria-label="Sign out">
        ${icons.logout}<span>Sign Out</span>
      </a>
    </li>
  </ul>

  <div class="sidebar-footer">
    <div class="sidebar-avatar" aria-hidden="true">${initials}</div>
    <div class="sidebar-user">
      <div class="sidebar-user-name">${displayName}</div>
      <div class="sidebar-user-role">${role}</div>
    </div>
  </div>
</nav>`;

  // ── STRAPLINE ────────────────────────────────────────────────
  const straplineHtml = `
<div class="strapline-strip" role="marquee" aria-label="University strapline">
  <div class="strapline-text" aria-hidden="true">
    THE UNIVERSITY OF OPPORTUNITY &nbsp;&nbsp;&#9670;&nbsp;&nbsp;
    INSPIRING ACHIEVEMENT &nbsp;&nbsp;&#9670;&nbsp;&nbsp;
    THE UNIVERSITY OF OPPORTUNITY &nbsp;&nbsp;&#9670;&nbsp;&nbsp;
    INSPIRING ACHIEVEMENT &nbsp;&nbsp;&#9670;&nbsp;&nbsp;
    THE UNIVERSITY OF OPPORTUNITY &nbsp;&nbsp;&#9670;&nbsp;&nbsp;
    INSPIRING ACHIEVEMENT &nbsp;&nbsp;&#9670;&nbsp;&nbsp;
  </div>
</div>`;

  // ── TOPBAR ───────────────────────────────────────────────────
  const pageLabel = {
    dashboard: 'Dashboard', events: 'Events', clubs: 'Club Hub',
    announcements: 'Announcements', facilities: 'Facilities',
    transport: 'Transport', checkin: 'Check-In & Rewards',
    'mental-health': 'Wellbeing', profile: 'My Profile', admin: 'Admin Panel'
  }[activeSection] || 'Campus Hub';

  const topbarHtml = `
<header class="topbar" role="banner">
  <div class="topbar-left">
    <button class="topbar-hamburger" id="btn-hamburger" aria-label="Toggle navigation menu" aria-expanded="false" aria-controls="main-sidebar">
      ${icons.hamburger}
    </button>
    <div class="topbar-greeting" aria-live="polite">Good day, ${displayName}</div>
    <div class="breadcrumbs" aria-label="Breadcrumb">
      <a href="dashboard.html">Home</a>
      ${activeSection && activeSection !== 'dashboard' ? `
        <span class="sep" aria-hidden="true">&#9670;</span>
        <span class="current">${pageLabel}</span>
      ` : ''}
    </div>
  </div>
  <div class="topbar-right">
    <div class="topbar-search" role="search">
      ${icons.search}
      <input type="search" placeholder="Search campus hub…" aria-label="Search campus hub" id="global-search" autocomplete="off"/>
    </div>
    <div style="position:relative">
      <button class="icon-btn has-notif" id="btn-notif" aria-label="Notifications (3 unread)" aria-haspopup="true" aria-expanded="false">
        ${icons.bell}
      </button>
      <div class="notif-panel hidden" id="notif-panel" role="dialog" aria-label="Notifications">
        <div class="notif-panel-header">
          <h4>Notifications</h4>
          <button id="btn-mark-read" aria-label="Mark all as read">Mark all read</button>
        </div>
        <div class="notif-item unread" tabindex="0" role="button">
          <div class="notif-dot"></div>
          <div>
            <div class="notif-title">New event: Spring Fair 2025</div>
            <div class="notif-time">2 minutes ago</div>
          </div>
        </div>
        <div class="notif-item unread" tabindex="0" role="button">
          <div class="notif-dot"></div>
          <div>
            <div class="notif-title">Your check-in points updated</div>
            <div class="notif-time">1 hour ago</div>
          </div>
        </div>
        <div class="notif-item" tabindex="0" role="button">
          <div class="notif-dot" style="background:transparent;border:2px solid rgba(77,81,104,0.2)"></div>
          <div>
            <div class="notif-title">Library booking confirmed</div>
            <div class="notif-time">Yesterday, 4:30 PM</div>
          </div>
        </div>
      </div>
    </div>
    <button class="icon-btn" id="btn-logout" aria-label="Sign out">
      ${icons.signout}
    </button>
  </div>
</header>`;

  // ── BOTTOM NAV (mobile) ──────────────────────────────────────
  const bottomNavHtml = `
<nav class="bottom-nav" aria-label="Mobile navigation">
  <div class="bottom-nav-inner">
    <a href="dashboard.html" class="bottom-nav-item ${activeSection==='dashboard'?'active':''}" aria-label="Dashboard">
      ${icons.home}<span>Home</span>
    </a>
    <a href="events.html" class="bottom-nav-item ${activeSection==='events'?'active':''}" aria-label="Events">
      ${icons.calendar}<span>Events</span>
    </a>
    <a href="facilities.html" class="bottom-nav-item ${activeSection==='facilities'?'active':''}" aria-label="Facilities">
      ${icons.grid}<span>Campus</span>
    </a>
    <a href="mental-health.html" class="bottom-nav-item ${activeSection==='mental-health'?'active':''}" aria-label="Wellbeing">
      ${icons.heart}<span>Wellbeing</span>
    </a>
    <a href="profile.html" class="bottom-nav-item ${activeSection==='profile'?'active':''}" aria-label="Profile">
      ${icons.user}<span>Profile</span>
    </a>
  </div>
</nav>`;

  // ── INJECT ───────────────────────────────────────────────────
  document.body.insertAdjacentHTML('afterbegin', sidebarHtml);
  document.body.insertAdjacentHTML('beforeend', bottomNavHtml);

  const existingContent = document.querySelector('.page-content') || document.querySelector('main');
  if (!existingContent) return;

  const wrapper = document.createElement('div');
  wrapper.className = 'main-layout';

  const mainDiv = document.createElement('div');
  mainDiv.className = 'main-content';
  mainDiv.id = 'main-content';
  mainDiv.insertAdjacentHTML('afterbegin', straplineHtml + topbarHtml);

  existingContent.parentNode.insertBefore(wrapper, existingContent);
  wrapper.appendChild(mainDiv);
  mainDiv.appendChild(existingContent);

  // ── LIVE DATE ────────────────────────────────────────────────
  // (date is in greeting now via JS)
  const greetingEl = document.querySelector('.topbar-greeting');
  if (greetingEl) {
    const opts = { weekday:'long', year:'numeric', month:'long', day:'numeric' };
    const dateStr = new Date().toLocaleDateString('en-GB', opts);
    const hour = new Date().getHours();
    const greetWord = hour < 12 ? 'Good morning' : hour < 17 ? 'Good afternoon' : 'Good evening';
    greetingEl.textContent = `${greetWord}, ${displayName}`;
    const dateEl = document.querySelector('.topbar-date');
    if (dateEl) dateEl.textContent = dateStr;
  }

  // ── MOBILE SIDEBAR TOGGLE ────────────────────────────────────
  const sidebar = document.getElementById('main-sidebar');
  const overlay = document.getElementById('sidebar-overlay');
  const hamburger = document.getElementById('btn-hamburger');

  function openSidebar() {
    sidebar.classList.add('open');
    overlay.classList.add('visible');
    hamburger.setAttribute('aria-expanded', 'true');
    document.body.style.overflow = 'hidden';
  }
  function closeSidebar() {
    sidebar.classList.remove('open');
    overlay.classList.remove('visible');
    hamburger.setAttribute('aria-expanded', 'false');
    document.body.style.overflow = '';
  }

  hamburger?.addEventListener('click', () => {
    sidebar.classList.contains('open') ? closeSidebar() : openSidebar();
  });
  overlay?.addEventListener('click', closeSidebar);

  // Close sidebar on Escape
  document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape' && sidebar.classList.contains('open')) closeSidebar();
  });

  // ── NOTIFICATION PANEL ───────────────────────────────────────
  const notifBtn = document.getElementById('btn-notif');
  const notifPanel = document.getElementById('notif-panel');

  notifBtn?.addEventListener('click', (e) => {
    e.stopPropagation();
    const isOpen = !notifPanel.classList.contains('hidden');
    notifPanel.classList.toggle('hidden');
    notifBtn.setAttribute('aria-expanded', String(!isOpen));
  });

  document.getElementById('btn-mark-read')?.addEventListener('click', () => {
    notifPanel.querySelectorAll('.notif-item.unread').forEach(el => {
      el.classList.remove('unread');
      const dot = el.querySelector('.notif-dot');
      if (dot) { dot.style.background = 'transparent'; dot.style.border = '2px solid rgba(77,81,104,0.2)'; }
    });
    notifBtn.classList.remove('has-notif');
  });

  document.addEventListener('click', (e) => {
    if (notifPanel && !notifPanel.classList.contains('hidden') && !notifBtn.contains(e.target) && !notifPanel.contains(e.target)) {
      notifPanel.classList.add('hidden');
      notifBtn.setAttribute('aria-expanded', 'false');
    }
  });

  // Close notif on Escape
  document.addEventListener('keydown', (e) => {
    if (e.key === 'Escape' && !notifPanel.classList.contains('hidden')) {
      notifPanel.classList.add('hidden');
      notifBtn.setAttribute('aria-expanded', 'false');
      notifBtn.focus();
    }
  });

  // ── AUTH ─────────────────────────────────────────────────────
  document.getElementById('btn-logout')?.addEventListener('click', () => Auth.logout());
  document.getElementById('btn-logout-sidebar')?.addEventListener('click', (e) => { e.preventDefault(); Auth.logout(); });

  // ── DEBOUNCED SEARCH ─────────────────────────────────────────
  const searchInput = document.getElementById('global-search');
  let searchTimeout;
  searchInput?.addEventListener('input', (e) => {
    clearTimeout(searchTimeout);
    searchTimeout = setTimeout(() => {
      const q = e.target.value.trim();
      if (q.length >= 2) {
        console.log('[CampusHub] Global search:', q);
        // Hook: dispatch custom event for page-level handlers
        document.dispatchEvent(new CustomEvent('campus:search', { detail: { query: q } }));
      }
    }, 350);
  });

  // ── SLIDING TAB INDICATOR ────────────────────────────────────
  function initTabIndicator(container) {
    const tabs = container;
    const active = tabs.querySelector('.tab-btn.active');
    let indicator = tabs.querySelector('.tab-indicator');
    if (!indicator) {
      indicator = document.createElement('div');
      indicator.className = 'tab-indicator';
      tabs.appendChild(indicator);
    }
    function moveIndicator(btn) {
      indicator.style.left  = btn.offsetLeft + 'px';
      indicator.style.width = btn.offsetWidth + 'px';
    }
    if (active) moveIndicator(active);
    tabs.querySelectorAll('.tab-btn').forEach(btn => {
      btn.addEventListener('click', () => moveIndicator(btn));
    });
  }
  document.querySelectorAll('.tabs').forEach(initTabIndicator);

  // ── COUNT-UP ANIMATION ───────────────────────────────────────
  function countUp(el, target, duration = 1200) {
    const start = 0;
    const startTime = performance.now();
    const isDecimal = String(target).includes('.');
    function tick(now) {
      const elapsed = now - startTime;
      const progress = Math.min(elapsed / duration, 1);
      const eased = 1 - Math.pow(1 - progress, 3);
      const current = start + (target - start) * eased;
      el.textContent = isDecimal ? current.toFixed(1) : Math.floor(current).toLocaleString();
      if (progress < 1) requestAnimationFrame(tick);
      else el.textContent = isDecimal ? target.toFixed(1) : Number(target).toLocaleString();
    }
    requestAnimationFrame(tick);
  }

  // Auto count-up for stat numbers
  const observer = new IntersectionObserver((entries) => {
    entries.forEach(entry => {
      if (entry.isIntersecting) {
        const el = entry.target;
        const raw = parseFloat(el.dataset.target || el.textContent.replace(/[^0-9.]/g, ''));
        if (!isNaN(raw) && raw > 0) countUp(el, raw);
        observer.unobserve(el);
      }
    });
  }, { threshold: 0.5 });

  document.querySelectorAll('.stat-number, .stat-value').forEach(el => {
    const n = parseFloat(el.dataset.target || el.textContent.replace(/[^0-9.]/g, ''));
    if (!isNaN(n) && n > 0) {
      el.dataset.target = n;
      el.textContent = '0';
      observer.observe(el);
    }
  });

  // ── COLLAPSIBLE WIDGETS ──────────────────────────────────────
  document.querySelectorAll('.widget-header').forEach(header => {
    const body = header.nextElementSibling;
    const toggle = header.querySelector('.widget-toggle');
    if (!body || !body.classList.contains('widget-body')) return;
    header.addEventListener('click', () => {
      const collapsed = body.classList.toggle('collapsed');
      if (toggle) toggle.classList.toggle('collapsed', collapsed);
      header.setAttribute('aria-expanded', String(!collapsed));
    });
  });

  // ── SESSION EXPIRY WARNING ───────────────────────────────────
  // Show warning 5 minutes before token expiry (if JWT exp available)
  try {
    const token = localStorage.getItem('token') || sessionStorage.getItem('token');
    if (token) {
      const payload = JSON.parse(atob(token.split('.')[1]));
      if (payload.exp) {
        const expiresIn = (payload.exp * 1000) - Date.now();
        const warnAt = expiresIn - (5 * 60 * 1000); // 5 mins before
        if (warnAt > 0) {
          setTimeout(() => {
            const banner = document.createElement('div');
            banner.className = 'session-warning';
            banner.innerHTML = `
              <svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="#FAC817" stroke-width="2" aria-hidden="true"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/></svg>
              Your session expires in <span class="warn-time">5 minutes</span>
              <button class="btn btn-primary btn-sm" onclick="location.reload()">Stay signed in</button>
              <button class="btn btn-outline btn-sm" onclick="this.closest('.session-warning').remove()" aria-label="Dismiss">Dismiss</button>
            `;
            document.body.appendChild(banner);
          }, Math.max(0, warnAt));
        }
      }
    }
  } catch(e) { /* non-JWT token, skip */ }

  // ── LAST UPDATED TIMESTAMPS ──────────────────────────────────
  document.querySelectorAll('[data-last-updated]').forEach(el => {
    const ts = new Date(el.dataset.lastUpdated);
    if (!isNaN(ts)) {
      const ago = Math.round((Date.now() - ts) / 60000);
      el.querySelector('.last-updated-text').textContent = ago < 1 ? 'just now' : ago < 60 ? `${ago}m ago` : `${Math.round(ago/60)}h ago`;
    }
  });
}

// ── TOAST UTILITY ─────────────────────────────────────────────────────────
window.showToast = function(title, message = '', type = 'default', duration = 4000) {
  let container = document.getElementById('toast-container');
  if (!container) {
    container = document.createElement('div');
    container.id = 'toast-container';
    container.className = 'toast-container';
    container.setAttribute('role', 'region');
    container.setAttribute('aria-label', 'Notifications');
    container.setAttribute('aria-live', 'polite');
    container.setAttribute('aria-atomic', 'false');
    document.body.appendChild(container);
  }
  const toast = document.createElement('div');
  toast.className = `toast${type !== 'default' ? ` toast-${type}` : ''}`;
  toast.setAttribute('role', 'alert');
  const icons = {
    success: `<svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="#59B985" stroke-width="2.5" aria-hidden="true"><polyline points="20,6 9,17 4,12"/></svg>`,
    error:   `<svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="#F5357F" stroke-width="2.5" aria-hidden="true"><circle cx="12" cy="12" r="10"/><line x1="15" y1="9" x2="9" y2="15"/><line x1="9" y1="9" x2="15" y2="15"/></svg>`,
    info:    `<svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="#4DBDD7" stroke-width="2.5" aria-hidden="true"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/></svg>`,
    default: `<svg width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="#FAC817" stroke-width="2.5" aria-hidden="true"><polygon points="12,2 15.09,8.26 22,9.27 17,14.14 18.18,21.02 12,17.77 5.82,21.02 7,14.14 2,9.27 8.91,8.26"/></svg>`
  };
  toast.innerHTML = `
    <div class="toast-header">
      <div class="toast-title" style="display:flex;align-items:center;gap:6px">${icons[type]||icons.default}${title}</div>
      <button class="toast-close" aria-label="Dismiss notification">&times;</button>
    </div>
    ${message ? `<div class="toast-body">${message}</div>` : ''}
    <div class="toast-progress" style="animation-duration:${duration}ms"></div>
  `;
  container.appendChild(toast);
  toast.querySelector('.toast-close').addEventListener('click', () => dismissToast(toast));
  setTimeout(() => dismissToast(toast), duration);
  function dismissToast(t) {
    t.classList.add('toast-out');
    setTimeout(() => t.remove(), 300);
  }
};

// ── CONFIRM DIALOG UTILITY ────────────────────────────────────────────────
window.showConfirm = function({ title, message, confirmText = 'Confirm', cancelText = 'Cancel', type = 'danger' } = {}) {
  return new Promise((resolve) => {
    const overlay = document.createElement('div');
    overlay.className = 'modal-overlay confirm-dialog';
    overlay.setAttribute('role', 'dialog');
    overlay.setAttribute('aria-modal', 'true');
    overlay.setAttribute('aria-labelledby', 'confirm-title');
    const iconSvg = type === 'danger'
      ? `<svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" aria-hidden="true"><circle cx="12" cy="12" r="10"/><line x1="12" y1="8" x2="12" y2="12"/><line x1="12" y1="16" x2="12.01" y2="16"/></svg>`
      : `<svg width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" aria-hidden="true"><path d="M10.29 3.86L1.82 18a2 2 0 0 0 1.71 3h16.94a2 2 0 0 0 1.71-3L13.71 3.86a2 2 0 0 0-3.42 0z"/><line x1="12" y1="9" x2="12" y2="13"/><line x1="12" y1="17" x2="12.01" y2="17"/></svg>`;
    overlay.innerHTML = `
      <div class="modal" style="max-width:380px">
        <div class="modal-body" style="padding:32px 28px">
          <div class="confirm-icon ${type}">${iconSvg}</div>
          <div class="confirm-title" id="confirm-title">${title}</div>
          <div class="confirm-message">${message}</div>
        </div>
        <div class="modal-footer">
          <button class="btn btn-outline btn-sm" id="confirm-cancel">${cancelText}</button>
          <button class="btn btn-${type==='danger'?'danger':'primary'} btn-sm" id="confirm-ok">${confirmText}</button>
        </div>
      </div>`;
    document.body.appendChild(overlay);
    const close = (result) => { overlay.remove(); resolve(result); };
    overlay.querySelector('#confirm-ok').addEventListener('click', () => close(true));
    overlay.querySelector('#confirm-cancel').addEventListener('click', () => close(false));
    overlay.addEventListener('click', (e) => { if (e.target === overlay) close(false); });
    document.addEventListener('keydown', function handler(e) {
      if (e.key === 'Escape') { close(false); document.removeEventListener('keydown', handler); }
    });
    overlay.querySelector('#confirm-ok').focus();
  });
};

// ── SKELETON HELPER ───────────────────────────────────────────────────────
window.showSkeleton = function(container, count = 3) {
  container.innerHTML = Array(count).fill('').map(() => `
    <div class="skeleton-card">
      <div class="skeleton skeleton-card-img"></div>
      <div class="skeleton skeleton-title"></div>
      <div class="skeleton skeleton-text w-80"></div>
      <div class="skeleton skeleton-text w-60"></div>
      <div style="display:flex;gap:8px;margin-top:12px">
        <div class="skeleton skeleton-badge"></div>
        <div class="skeleton skeleton-badge"></div>
      </div>
    </div>`).join('');
};

// ── PASSWORD STRENGTH ─────────────────────────────────────────────────────
window.initPasswordStrength = function(inputId) {
  const input = document.getElementById(inputId);
  if (!input) return;
  const wrap = input.closest('.form-group') || input.parentElement;
  const strengthEl = document.createElement('div');
  strengthEl.className = 'password-strength';
  strengthEl.innerHTML = `<div class="strength-bar"><div class="strength-fill s0" id="strength-fill-${inputId}"></div></div><div class="strength-label" id="strength-label-${inputId}"></div>`;
  input.parentElement.insertAdjacentElement('afterend', strengthEl);

  input.addEventListener('input', () => {
    const v = input.value;
    let score = 0;
    if (v.length >= 8) score++;
    if (/[A-Z]/.test(v)) score++;
    if (/[0-9]/.test(v)) score++;
    if (/[^A-Za-z0-9]/.test(v)) score++;
    const fill = document.getElementById(`strength-fill-${inputId}`);
    const label = document.getElementById(`strength-label-${inputId}`);
    fill.className = `strength-fill s${score}`;
    const labels = ['', 'Weak', 'Fair', 'Good', 'Strong'];
    label.textContent = v ? labels[score] : '';
    label.className = `strength-label s${score}`;
  });
};

// ── SHOW/HIDE PASSWORD ────────────────────────────────────────────────────
window.initPasswordToggle = function(inputId) {
  const input = document.getElementById(inputId);
  if (!input) return;
  const btn = document.createElement('button');
  btn.type = 'button';
  btn.className = 'pass-toggle';
  btn.setAttribute('aria-label', 'Show password');
  btn.innerHTML = `<svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" aria-hidden="true"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/><circle cx="12" cy="12" r="3"/></svg>`;
  input.parentElement.style.position = 'relative';
  input.style.paddingRight = '42px';
  input.parentElement.appendChild(btn);
  let visible = false;
  btn.addEventListener('click', () => {
    visible = !visible;
    input.type = visible ? 'text' : 'password';
    btn.setAttribute('aria-label', visible ? 'Hide password' : 'Show password');
    btn.innerHTML = visible
      ? `<svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" aria-hidden="true"><path d="M17.94 17.94A10.07 10.07 0 0 1 12 20c-7 0-11-8-11-8a18.45 18.45 0 0 1 5.06-5.94"/><path d="M9.9 4.24A9.12 9.12 0 0 1 12 4c7 0 11 8 11 8a18.5 18.5 0 0 1-2.16 3.19"/><line x1="1" y1="1" x2="23" y2="23"/></svg>`
      : `<svg width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" aria-hidden="true"><path d="M1 12s4-8 11-8 11 8 11 8-4 8-11 8-11-8-11-8z"/><circle cx="12" cy="12" r="3"/></svg>`;
  });
};

// ── CHAR COUNTER ──────────────────────────────────────────────────────────
window.initCharCounter = function(inputId, max) {
  const input = document.getElementById(inputId);
  if (!input) return;
  const counter = document.createElement('div');
  counter.className = 'char-counter';
  counter.textContent = `0 / ${max}`;
  input.parentElement.appendChild(counter);
  input.setAttribute('maxlength', max);
  input.addEventListener('input', () => {
    const len = input.value.length;
    counter.textContent = `${len} / ${max}`;
    counter.className = 'char-counter' + (len >= max ? ' at-limit' : len >= max * 0.85 ? ' near-limit' : '');
  });
};
