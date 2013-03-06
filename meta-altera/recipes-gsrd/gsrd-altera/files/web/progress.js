interval_msec = 2000;
debug = false;

function update() {
  // Handle the various browsers... Pre-IE7 doesn't support XMLHttpRequest.
  if (window.XMLHttpRequest && !(window.ActiveXObject)) {
    try {
      req = new XMLHttpRequest();
    } catch(e) {
      req = false;
    }
  // IE/Windows ActiveX
    } else if (window.ActiveXObject) {
      try {
        req = new ActiveXObject("Msxml2.XMLHTTP");
      } catch(e) {
        try {
          req = new ActiveXObject("Microsoft.XMLHTTP");
      } catch(e) {
        req = false;
      }
    }
  }

  // If request isn't possible, just return right away.
  if (!req) return;

  req.open("GET","/PROGRESS", false);
  req.send(null);
  if(req.status == 200) {
    // The server sent a response.
    var progress = eval(req.responseText);
    if(debug) {
      document.getElementById('debug_panel').innerHTML += "speed = " + progress.speed + " " + progress.percent + "%" + '<br />';
    }
    bar = document.getElementById('progressbar');
    if(progress.state == 'done'){
      w = 540;
      bar.style.width = w + 'px';
      window.clearInterval(interval);
      window.clearTimeout(interval);
      return;
    }
    w = (progress.percent/100) * 520;
    bar.style.width = w + 'px';
    document.getElementById('tp').innerHTML = progress.percent + "%";
  }
  else {
    // Something didn't work....at least set an alert with useful information!
    alert("Error " + request.status + ":  " + request.statusText );
  }
}

function startProgress() {
  // Code needs to go here to generate the progress bar.
  interval = window.setInterval(
    function () {
      update();
    },
    interval_msec
  );
};

