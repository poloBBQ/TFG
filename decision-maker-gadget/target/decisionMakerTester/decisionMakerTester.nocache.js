function decisionMakerTester(){
  var $wnd_0 = window, $doc_0 = document, $stats = $wnd_0.__gwtStatsEvent?function(a){
    return $wnd_0.__gwtStatsEvent(a);
  }
  :null, $sessionId_0 = $wnd_0.__gwtStatsSessionId?$wnd_0.__gwtStatsSessionId:null, scriptsDone, loadDone, bodyDone, base = '', metaProps = {}, values = [], providers = [], answers = [], softPermutationId = 0, onLoadErrorFunc, propertyErrorFunc;
  $stats && $stats({moduleName:'decisionMakerTester', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'bootstrap', millis:(new Date).getTime(), type:'begin'});
  if (!$wnd_0.__gwt_stylesLoaded) {
    $wnd_0.__gwt_stylesLoaded = {};
  }
  if (!$wnd_0.__gwt_scriptsLoaded) {
    $wnd_0.__gwt_scriptsLoaded = {};
  }
  function isHostedMode(){
    var result = false;
    try {
      var query = $wnd_0.location.search;
      return (query.indexOf('gwt.codesvr=') != -1 || (query.indexOf('gwt.hosted=') != -1 || $wnd_0.external && $wnd_0.external.gwtOnLoad)) && query.indexOf('gwt.hybrid') == -1;
    }
     catch (e) {
    }
    isHostedMode = function(){
      return result;
    }
    ;
    return result;
  }

  function maybeStartModule(){
    if (scriptsDone && loadDone) {
      var iframe = $doc_0.getElementById('decisionMakerTester');
      var frameWnd = iframe.contentWindow;
      if (isHostedMode()) {
        frameWnd.__gwt_getProperty = function(name_0){
          return computePropValue(name_0);
        }
        ;
      }
      decisionMakerTester = null;
      frameWnd.gwtOnLoad(onLoadErrorFunc, 'decisionMakerTester', base, softPermutationId);
      $stats && $stats({moduleName:'decisionMakerTester', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'moduleStartup', millis:(new Date).getTime(), type:'end'});
    }
  }

  function computeScriptBase(){
    if (metaProps['baseUrl']) {
      base = metaProps['baseUrl'];
      return base;
    }
    var thisScript;
    var scriptTags = $doc_0.getElementsByTagName('script');
    for (var i = 0; i < scriptTags.length; ++i) {
      if (scriptTags[i].src.indexOf('decisionMakerTester.nocache.js') != -1) {
        thisScript = scriptTags[i];
      }
    }
    if (!thisScript) {
      var markerId = '__gwt_marker_decisionMakerTester';
      var markerScript;
      $doc_0.write('<script id="' + markerId + '"><\/script>');
      markerScript = $doc_0.getElementById(markerId);
      thisScript = markerScript && markerScript.previousSibling;
      while (thisScript && thisScript.tagName != 'SCRIPT') {
        thisScript = thisScript.previousSibling;
      }
    }
    function getDirectoryOfFile(path){
      var hashIndex = path.lastIndexOf('#');
      if (hashIndex == -1) {
        hashIndex = path.length;
      }
      var queryIndex = path.indexOf('?');
      if (queryIndex == -1) {
        queryIndex = path.length;
      }
      var slashIndex = path.lastIndexOf('/', Math.min(queryIndex, hashIndex));
      return slashIndex >= 0?path.substring(0, slashIndex + 1):'';
    }

    ;
    if (thisScript && thisScript.src) {
      base = getDirectoryOfFile(thisScript.src);
    }
    if (base == '') {
      var baseElements = $doc_0.getElementsByTagName('base');
      if (baseElements.length > 0) {
        base = baseElements[baseElements.length - 1].href;
      }
       else {
        base = getDirectoryOfFile($doc_0.location.href);
      }
    }
     else if (base.match(/^\w+:\/\//)) {
    }
     else {
      var img = $doc_0.createElement('img');
      img.src = base + 'clear.cache.gif';
      base = getDirectoryOfFile(img.src);
    }
    if (markerScript) {
      markerScript.parentNode.removeChild(markerScript);
    }
    return base;
  }

  function processMetas(){
    var metas = document.getElementsByTagName('meta');
    for (var i = 0, n = metas.length; i < n; ++i) {
      var meta = metas[i], name_0 = meta.getAttribute('name'), content_0;
      if (name_0) {
        name_0 = name_0.replace('decisionMakerTester::', '');
        if (name_0.indexOf('::') >= 0) {
          continue;
        }
        if (name_0 == 'gwt:property') {
          content_0 = meta.getAttribute('content');
          if (content_0) {
            var value, eq = content_0.indexOf('=');
            if (eq >= 0) {
              name_0 = content_0.substring(0, eq);
              value = content_0.substring(eq + 1);
            }
             else {
              name_0 = content_0;
              value = '';
            }
            metaProps[name_0] = value;
          }
        }
         else if (name_0 == 'gwt:onPropertyErrorFn') {
          content_0 = meta.getAttribute('content');
          if (content_0) {
            try {
              propertyErrorFunc = eval(content_0);
            }
             catch (e) {
              alert('Bad handler "' + content_0 + '" for "gwt:onPropertyErrorFn"');
            }
          }
        }
         else if (name_0 == 'gwt:onLoadErrorFn') {
          content_0 = meta.getAttribute('content');
          if (content_0) {
            try {
              onLoadErrorFunc = eval(content_0);
            }
             catch (e) {
              alert('Bad handler "' + content_0 + '" for "gwt:onLoadErrorFn"');
            }
          }
        }
      }
    }
  }

  function __gwt_isKnownPropertyValue(propName, propValue){
    return propValue in values[propName];
  }

  function __gwt_getMetaProperty(name_0){
    var value = metaProps[name_0];
    return value == null?null:value;
  }

  function unflattenKeylistIntoAnswers(propValArray, value){
    var answer = answers;
    for (var i = 0, n = propValArray.length - 1; i < n; ++i) {
      answer = answer[propValArray[i]] || (answer[propValArray[i]] = []);
    }
    answer[propValArray[n]] = value;
  }

  function computePropValue(propName){
    var value = providers[propName](), allowedValuesMap = values[propName];
    if (value in allowedValuesMap) {
      return value;
    }
    var allowedValuesList = [];
    for (var k in allowedValuesMap) {
      allowedValuesList[allowedValuesMap[k]] = k;
    }
    if (propertyErrorFunc) {
      propertyErrorFunc(propName, allowedValuesList, value);
    }
    throw null;
  }

  var frameInjected;
  function maybeInjectFrame(){
    if (!frameInjected) {
      frameInjected = true;
      var iframe = $doc_0.createElement('iframe');
      iframe.src = "javascript:''";
      iframe.id = 'decisionMakerTester';
      iframe.style.cssText = 'position:absolute;width:0;height:0;border:none';
      iframe.tabIndex = -1;
      $doc_0.body.appendChild(iframe);
      $stats && $stats({moduleName:'decisionMakerTester', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'moduleStartup', millis:(new Date).getTime(), type:'moduleRequested'});
      iframe.contentWindow.location.replace(base + initialHtml);
    }
  }

  providers['locale'] = function(){
    try {
      var locale;
      var defaultLocale = 'en' || 'default';
      if (locale == null) {
        var args = location.search;
        var startLang = args.indexOf('locale=');
        if (startLang >= 0) {
          var language = args.substring(startLang);
          var begin = language.indexOf('=') + 1;
          var end = language.indexOf('&');
          if (end == -1) {
            end = language.length;
          }
          locale = language.substring(begin, end);
        }
      }
      if (locale == null) {
        locale = __gwt_getMetaProperty('locale');
      }
      if (locale == null) {
        locale = $wnd_0['__gwt_Locale'];
      }
       else {
        $wnd_0['__gwt_Locale'] = locale || defaultLocale;
      }
      if (locale == null) {
        return defaultLocale;
      }
      while (!__gwt_isKnownPropertyValue('locale', locale)) {
        var lastIndex = locale.lastIndexOf('_');
        if (lastIndex == -1) {
          locale = defaultLocale;
          break;
        }
         else {
          locale = locale.substring(0, lastIndex);
        }
      }
      return locale;
    }
     catch (e) {
      alert('Unexpected exception in locale detection, using default: ' + e);
      return 'default';
    }
  }
  ;
  values['locale'] = {'default':0, en:1, es:2};
  providers['user.agent'] = function(){
    var ua = navigator.userAgent.toLowerCase();
    var makeVersion = function(result){
      return parseInt(result[1]) * 1000 + parseInt(result[2]);
    }
    ;
    if (ua.indexOf('opera') != -1) {
      return 'opera';
    }
     else if (ua.indexOf('webkit') != -1) {
      return 'safari';
    }
     else if (ua.indexOf('msie') != -1) {
      if (document.documentMode >= 8) {
        return 'ie8';
      }
       else {
        var result_0 = /msie ([0-9]+)\.([0-9]+)/.exec(ua);
        if (result_0 && result_0.length == 3) {
          var v = makeVersion(result_0);
          if (v >= 6000) {
            return 'ie6';
          }
        }
      }
    }
     else if (ua.indexOf('gecko') != -1) {
      return 'gecko1_8';
    }
    return 'unknown';
  }
  ;
  values['user.agent'] = {gecko1_8:0, ie6:1, ie8:2, opera:3, safari:4};
  decisionMakerTester.onScriptLoad = function(){
    if (frameInjected) {
      loadDone = true;
      maybeStartModule();
    }
  }
  ;
  decisionMakerTester.onInjectionDone = function(){
    scriptsDone = true;
    $stats && $stats({moduleName:'decisionMakerTester', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'loadExternalRefs', millis:(new Date).getTime(), type:'end'});
    maybeStartModule();
  }
  ;
  processMetas();
  computeScriptBase();
  var strongName;
  var initialHtml;
  if (isHostedMode()) {
    if ($wnd_0.external && ($wnd_0.external.initModule && $wnd_0.external.initModule('decisionMakerTester'))) {
      $wnd_0.location.reload();
      return;
    }
    initialHtml = 'hosted.html?decisionMakerTester';
    strongName = '';
  }
  $stats && $stats({moduleName:'decisionMakerTester', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'bootstrap', millis:(new Date).getTime(), type:'selectingPermutation'});
  if (!isHostedMode()) {
    try {
      unflattenKeylistIntoAnswers(['es', 'safari'], '1341CFCABA0FE5FC91AB64E6DC50708C');
      unflattenKeylistIntoAnswers(['default', 'safari'], '2FE5D944C10E18D81C652D485D996913');
      unflattenKeylistIntoAnswers(['en', 'safari'], '2FE5D944C10E18D81C652D485D996913');
      unflattenKeylistIntoAnswers(['es', 'ie6'], '3BAFEB88B7B0D167A4792E8083BF3546');
      unflattenKeylistIntoAnswers(['es', 'ie8'], '5C848EC2B450CB18AD220A1380552284');
      unflattenKeylistIntoAnswers(['default', 'ie6'], '8F43767246C7EE78B58BDA0764C01F81');
      unflattenKeylistIntoAnswers(['en', 'ie6'], '8F43767246C7EE78B58BDA0764C01F81');
      unflattenKeylistIntoAnswers(['default', 'ie8'], '99C79A7B24A7C7C718A38D8A33A3F772');
      unflattenKeylistIntoAnswers(['en', 'ie8'], '99C79A7B24A7C7C718A38D8A33A3F772');
      unflattenKeylistIntoAnswers(['default', 'gecko1_8'], '9DDB781050710C297644E863AD998931');
      unflattenKeylistIntoAnswers(['en', 'gecko1_8'], '9DDB781050710C297644E863AD998931');
      unflattenKeylistIntoAnswers(['es', 'gecko1_8'], 'C4222011C2C616DB172AD5C4F0F47454');
      unflattenKeylistIntoAnswers(['es', 'opera'], 'FE7DF2017DC1D94C68EE8A077866953D');
      unflattenKeylistIntoAnswers(['default', 'opera'], 'FF0A2EF4B286E4C439D5C1DDC5563890');
      unflattenKeylistIntoAnswers(['en', 'opera'], 'FF0A2EF4B286E4C439D5C1DDC5563890');
      strongName = answers[computePropValue('locale')][computePropValue('user.agent')];
      var idx = strongName.indexOf(':');
      if (idx != -1) {
        softPermutationId = Number(strongName.substring(idx + 1));
        strongName = strongName.substring(0, idx);
      }
      initialHtml = strongName + '.cache.html';
    }
     catch (e) {
      return;
    }
  }
  var onBodyDoneTimerId;
  function onBodyDone(){
    if (!bodyDone) {
      bodyDone = true;
      if (!__gwt_stylesLoaded['decisionMaker.css']) {
        var l = $doc_0.createElement('link');
        __gwt_stylesLoaded['decisionMaker.css'] = l;
        l.setAttribute('rel', 'stylesheet');
        l.setAttribute('href', base + 'decisionMaker.css');
        $doc_0.getElementsByTagName('head')[0].appendChild(l);
      }
      maybeStartModule();
      if ($doc_0.removeEventListener) {
        $doc_0.removeEventListener('DOMContentLoaded', onBodyDone, false);
      }
      if (onBodyDoneTimerId) {
        clearInterval(onBodyDoneTimerId);
      }
    }
  }

  if ($doc_0.addEventListener) {
    $doc_0.addEventListener('DOMContentLoaded', function(){
      maybeInjectFrame();
      onBodyDone();
    }
    , false);
  }
  var onBodyDoneTimerId = setInterval(function(){
    if (/loaded|complete/.test($doc_0.readyState)) {
      maybeInjectFrame();
      onBodyDone();
    }
  }
  , 50);
  $stats && $stats({moduleName:'decisionMakerTester', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'bootstrap', millis:(new Date).getTime(), type:'end'});
  $stats && $stats({moduleName:'decisionMakerTester', sessionId:$sessionId_0, subSystem:'startup', evtGroup:'loadExternalRefs', millis:(new Date).getTime(), type:'begin'});
  $doc_0.write('<script defer="defer">decisionMakerTester.onInjectionDone(\'decisionMakerTester\')<\/script>');
}

decisionMakerTester();
