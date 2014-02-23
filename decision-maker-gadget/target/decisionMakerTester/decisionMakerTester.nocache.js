function decisionMakerTester(){
  var $intern_0 = '', $intern_34 = '" for "gwt:onLoadErrorFn"', $intern_32 = '" for "gwt:onPropertyErrorFn"', $intern_15 = '"><\/script>', $intern_17 = '#', $intern_43 = '&', $intern_55 = '.cache.html', $intern_19 = '/', $intern_54 = ':', $intern_53 = ':1', $intern_26 = '::', $intern_63 = '<script defer="defer">decisionMakerTester.onInjectionDone(\'decisionMakerTester\')<\/script>', $intern_14 = '<script id="', $intern_29 = '=', $intern_18 = '?', $intern_51 = 'ABB1F6C2EDDDE3A5F59B43F34CE51CC7', $intern_52 = 'AF50D3C15C3C4F49E56AC8D32FAD4E29', $intern_31 = 'Bad handler "', $intern_62 = 'DOMContentLoaded', $intern_16 = 'SCRIPT', $intern_46 = 'Unexpected exception in locale detection, using default: ', $intern_45 = '_', $intern_44 = '__gwt_Locale', $intern_13 = '__gwt_marker_decisionMakerTester', $intern_20 = 'base', $intern_10 = 'baseUrl', $intern_4 = 'begin', $intern_3 = 'bootstrap', $intern_22 = 'clear.cache.gif', $intern_28 = 'content', $intern_56 = 'decisionMaker.css', $intern_1 = 'decisionMakerTester', $intern_12 = 'decisionMakerTester.nocache.js', $intern_25 = 'decisionMakerTester::', $intern_41 = 'default', $intern_40 = 'en', $intern_9 = 'end', $intern_50 = 'es', $intern_5 = 'gwt.codesvr=', $intern_6 = 'gwt.hosted=', $intern_7 = 'gwt.hybrid', $intern_33 = 'gwt:onLoadErrorFn', $intern_30 = 'gwt:onPropertyErrorFn', $intern_27 = 'gwt:property', $intern_61 = 'head', $intern_48 = 'hosted.html?decisionMakerTester', $intern_60 = 'href', $intern_35 = 'iframe', $intern_21 = 'img', $intern_36 = "javascript:''", $intern_57 = 'link', $intern_47 = 'loadExternalRefs', $intern_39 = 'locale', $intern_42 = 'locale=', $intern_23 = 'meta', $intern_38 = 'moduleRequested', $intern_8 = 'moduleStartup', $intern_24 = 'name', $intern_37 = 'position:absolute;width:0;height:0;border:none', $intern_58 = 'rel', $intern_11 = 'script', $intern_49 = 'selectingPermutation', $intern_2 = 'startup', $intern_59 = 'stylesheet';
  var $wnd = window, $doc = document, $stats = $wnd.__gwtStatsEvent?function(a){
    return $wnd.__gwtStatsEvent(a);
  }
  :null, $sessionId = $wnd.__gwtStatsSessionId?$wnd.__gwtStatsSessionId:null, scriptsDone, loadDone, bodyDone, base = $intern_0, metaProps = {}, values = [], providers = [], answers = [], softPermutationId = 0, onLoadErrorFunc, propertyErrorFunc;
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date).getTime(), type:$intern_4});
  if (!$wnd.__gwt_stylesLoaded) {
    $wnd.__gwt_stylesLoaded = {};
  }
  if (!$wnd.__gwt_scriptsLoaded) {
    $wnd.__gwt_scriptsLoaded = {};
  }
  function isHostedMode(){
    var result = false;
    try {
      var query = $wnd.location.search;
      return (query.indexOf($intern_5) != -1 || (query.indexOf($intern_6) != -1 || $wnd.external && $wnd.external.gwtOnLoad)) && query.indexOf($intern_7) == -1;
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
      var iframe = $doc.getElementById($intern_1);
      var frameWnd = iframe.contentWindow;
      if (isHostedMode()) {
        frameWnd.__gwt_getProperty = function(name){
          return computePropValue(name);
        }
        ;
      }
      decisionMakerTester = null;
      frameWnd.gwtOnLoad(onLoadErrorFunc, $intern_1, base, softPermutationId);
      $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_8, millis:(new Date).getTime(), type:$intern_9});
    }
  }

  function computeScriptBase(){
    if (metaProps[$intern_10]) {
      base = metaProps[$intern_10];
      return base;
    }
    var thisScript;
    var scriptTags = $doc.getElementsByTagName($intern_11);
    for (var i = 0; i < scriptTags.length; ++i) {
      if (scriptTags[i].src.indexOf($intern_12) != -1) {
        thisScript = scriptTags[i];
      }
    }
    if (!thisScript) {
      var markerId = $intern_13;
      var markerScript;
      $doc.write($intern_14 + markerId + $intern_15);
      markerScript = $doc.getElementById(markerId);
      thisScript = markerScript && markerScript.previousSibling;
      while (thisScript && thisScript.tagName != $intern_16) {
        thisScript = thisScript.previousSibling;
      }
    }
    function getDirectoryOfFile(path){
      var hashIndex = path.lastIndexOf($intern_17);
      if (hashIndex == -1) {
        hashIndex = path.length;
      }
      var queryIndex = path.indexOf($intern_18);
      if (queryIndex == -1) {
        queryIndex = path.length;
      }
      var slashIndex = path.lastIndexOf($intern_19, Math.min(queryIndex, hashIndex));
      return slashIndex >= 0?path.substring(0, slashIndex + 1):$intern_0;
    }

    ;
    if (thisScript && thisScript.src) {
      base = getDirectoryOfFile(thisScript.src);
    }
    if (base == $intern_0) {
      var baseElements = $doc.getElementsByTagName($intern_20);
      if (baseElements.length > 0) {
        base = baseElements[baseElements.length - 1].href;
      }
       else {
        base = getDirectoryOfFile($doc.location.href);
      }
    }
     else if (base.match(/^\w+:\/\//)) {
    }
     else {
      var img = $doc.createElement($intern_21);
      img.src = base + $intern_22;
      base = getDirectoryOfFile(img.src);
    }
    if (markerScript) {
      markerScript.parentNode.removeChild(markerScript);
    }
    return base;
  }

  function processMetas(){
    var metas = document.getElementsByTagName($intern_23);
    for (var i = 0, n = metas.length; i < n; ++i) {
      var meta = metas[i], name = meta.getAttribute($intern_24), content;
      if (name) {
        name = name.replace($intern_25, $intern_0);
        if (name.indexOf($intern_26) >= 0) {
          continue;
        }
        if (name == $intern_27) {
          content = meta.getAttribute($intern_28);
          if (content) {
            var value, eq = content.indexOf($intern_29);
            if (eq >= 0) {
              name = content.substring(0, eq);
              value = content.substring(eq + 1);
            }
             else {
              name = content;
              value = $intern_0;
            }
            metaProps[name] = value;
          }
        }
         else if (name == $intern_30) {
          content = meta.getAttribute($intern_28);
          if (content) {
            try {
              propertyErrorFunc = eval(content);
            }
             catch (e) {
              alert($intern_31 + content + $intern_32);
            }
          }
        }
         else if (name == $intern_33) {
          content = meta.getAttribute($intern_28);
          if (content) {
            try {
              onLoadErrorFunc = eval(content);
            }
             catch (e) {
              alert($intern_31 + content + $intern_34);
            }
          }
        }
      }
    }
  }

  function __gwt_isKnownPropertyValue(propName, propValue){
    return propValue in values[propName];
  }

  function __gwt_getMetaProperty(name){
    var value = metaProps[name];
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
      var iframe = $doc.createElement($intern_35);
      iframe.src = $intern_36;
      iframe.id = $intern_1;
      iframe.style.cssText = $intern_37;
      iframe.tabIndex = -1;
      $doc.body.appendChild(iframe);
      $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_8, millis:(new Date).getTime(), type:$intern_38});
      iframe.contentWindow.location.replace(base + initialHtml);
    }
  }

  providers[$intern_39] = function(){
    try {
      var locale;
      var defaultLocale = $intern_40 || $intern_41;
      if (locale == null) {
        var args = location.search;
        var startLang = args.indexOf($intern_42);
        if (startLang >= 0) {
          var language = args.substring(startLang);
          var begin = language.indexOf($intern_29) + 1;
          var end = language.indexOf($intern_43);
          if (end == -1) {
            end = language.length;
          }
          locale = language.substring(begin, end);
        }
      }
      if (locale == null) {
        locale = __gwt_getMetaProperty($intern_39);
      }
      if (locale == null) {
        locale = $wnd[$intern_44];
      }
       else {
        $wnd[$intern_44] = locale || defaultLocale;
      }
      if (locale == null) {
        return defaultLocale;
      }
      while (!__gwt_isKnownPropertyValue($intern_39, locale)) {
        var lastIndex = locale.lastIndexOf($intern_45);
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
      alert($intern_46 + e);
      return $intern_41;
    }
  }
  ;
  values[$intern_39] = {'default':0, en:1, es:2};
  decisionMakerTester.onScriptLoad = function(){
    if (frameInjected) {
      loadDone = true;
      maybeStartModule();
    }
  }
  ;
  decisionMakerTester.onInjectionDone = function(){
    scriptsDone = true;
    $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_47, millis:(new Date).getTime(), type:$intern_9});
    maybeStartModule();
  }
  ;
  processMetas();
  computeScriptBase();
  var strongName;
  var initialHtml;
  if (isHostedMode()) {
    if ($wnd.external && ($wnd.external.initModule && $wnd.external.initModule($intern_1))) {
      $wnd.location.reload();
      return;
    }
    initialHtml = $intern_48;
    strongName = $intern_0;
  }
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date).getTime(), type:$intern_49});
  if (!isHostedMode()) {
    try {
      unflattenKeylistIntoAnswers([$intern_50], $intern_51);
      unflattenKeylistIntoAnswers([$intern_41], $intern_52);
      unflattenKeylistIntoAnswers([$intern_40], $intern_52);
      unflattenKeylistIntoAnswers([$intern_41], $intern_52 + $intern_53);
      unflattenKeylistIntoAnswers([$intern_40], $intern_52 + $intern_53);
      strongName = answers[computePropValue($intern_39)];
      var idx = strongName.indexOf($intern_54);
      if (idx != -1) {
        softPermutationId = Number(strongName.substring(idx + 1));
        strongName = strongName.substring(0, idx);
      }
      initialHtml = strongName + $intern_55;
    }
     catch (e) {
      return;
    }
  }
  var onBodyDoneTimerId;
  function onBodyDone(){
    if (!bodyDone) {
      bodyDone = true;
      if (!__gwt_stylesLoaded[$intern_56]) {
        var l = $doc.createElement($intern_57);
        __gwt_stylesLoaded[$intern_56] = l;
        l.setAttribute($intern_58, $intern_59);
        l.setAttribute($intern_60, base + $intern_56);
        $doc.getElementsByTagName($intern_61)[0].appendChild(l);
      }
      maybeStartModule();
      if ($doc.removeEventListener) {
        $doc.removeEventListener($intern_62, onBodyDone, false);
      }
      if (onBodyDoneTimerId) {
        clearInterval(onBodyDoneTimerId);
      }
    }
  }

  if ($doc.addEventListener) {
    $doc.addEventListener($intern_62, function(){
      maybeInjectFrame();
      onBodyDone();
    }
    , false);
  }
  var onBodyDoneTimerId = setInterval(function(){
    if (/loaded|complete/.test($doc.readyState)) {
      maybeInjectFrame();
      onBodyDone();
    }
  }
  , 50);
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_3, millis:(new Date).getTime(), type:$intern_9});
  $stats && $stats({moduleName:$intern_1, sessionId:$sessionId, subSystem:$intern_2, evtGroup:$intern_47, millis:(new Date).getTime(), type:$intern_4});
  $doc.write($intern_63);
}

decisionMakerTester();
