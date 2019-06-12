;(function(global,$){var _lang={'zhs':'zhs','zht':'zht'};function _convertObjectByLang(obj,lang){if(!obj||typeof obj!=='object'){return obj;}
lang=lang||_lang.zhs;var ret=$.isArray(obj)?[]:{},item=null;for(var prop in obj){item=obj[prop];if(obj.hasOwnProperty(prop)){if(typeof item==='string'){if(lang===_lang.zht){ret[prop]=_util.sim2tra(item);}else if(lang===_lang.zhs){ret[prop]=cnConvert.tra2sim(item);}}else if(typeof item==="object"){ret[prop]=_convertObjectByLang(item,lang);}else{ret[prop]=item;}}}
return ret;}
function _openLoadingTip(isShowLoading){if(isShowLoading){$('#requestLoadingTip').show();}}
function _closeLoadingTip(isShowLoading){if(isShowLoading){$('#requestLoadingTip').hide();}}
var _util={initNameSpace:function(router,root){if(!router||router===''){return;}
var p=root||window,arrNS=router.split('.');for(var i=0,len=arrNS.length;i<len;i++){if(!p[arrNS[i]]){p[arrNS[i]]={};}
p=p[arrNS[i]];}},isLogin:function(){return!!$.cookie('skey')&&!!$.cookie('uin');},replaceTpl:function(str,conf){return(""+str).replace(/\$(\w+)\$/g,function(a,b){return typeof conf[b]!="undefined"?conf[b]:"$"+b+"$";});},getQueryByName:function(name,str){str=str||window.location.href;var qIndex=str.indexOf('?');if(qIndex===-1){return null;}
var query=str.substr(qIndex),result=query.match(new RegExp("[\?\&]"+name+"=([^\&]+)","i"));if(result===null||result.length<1){return'';}
return result[1];},throttle:function(fn,context){if(typeof fn!=='function'){return;}
if(fn.timeoutId){clearTimeout(fn.timeoutId);}
fn.timeoutId=setTimeout(function(){fn.call(context);},100);},isPositiveInteger:function(number){return /^[0-9]*[1-9][0-9]*$/.test(number);},strlen:function(str){return(_util.isIE()&&str.indexOf('\n')!=-1)?str.replace(/\r?\n/g,'_').length:str.length;},mb_strlen:function(str){var len=0;for(var i=0;i<str.length;i++){len+=str.charCodeAt(i)<0||str.charCodeAt(i)>255?3:1;}
return len;},mb_cutstr:function(str,maxlen,dot){dot=!dot?'...':dot;maxlen=maxlen-dot.length;var len=0;var ret='';for(var i=0;i<str.length;i++){len+=str.charCodeAt(i)<0||str.charCodeAt(i)>255?3:1;if(len>maxlen){ret+=dot;break;}
ret+=str.substr(i,1);}
return ret;},getUserLang:function(){return typeof userLang==='undefined'?_lang.zhs:userLang;},sim2tra:function(obj){if(typeof cnConvert!=='undefined'&&this.getUserLang()===_lang.zht){if(typeof obj==='string'){return cnConvert.sim2tra(obj);}else if(typeof obj==='object'){return _convertObjectByLang(obj,_lang.zht);}}
return obj;},tra2sim:function(obj){if(typeof cnConvert!=='undefined'&&this.getUserLang()===_lang.zht){if(typeof obj==='string'){return cnConvert.tra2sim(obj);}else if(typeof obj==='object'){return _convertObjectByLang(obj,_lang.zhs);}}
return obj;},request:function(option){if(!('timeout'in option)){option.timeout=30000;}
if(!('isShowLoading'in option)){option.isShowLoading=true;}
if('type'in option&&option.type.toLowerCase()==='post'){var dataValue=$.cookie('pubtoken');if(dataValue){var dataKey='_token';if(!option.data){option.data={};}
if(!option.data[dataKey]){option.data[dataKey]=dataValue;}}}
_openLoadingTip(option.isShowLoading);var me=this,successFn=option.success,errorFn=option.error;option.success=function(data,textStatus,jqXHR){_closeLoadingTip(option.isShowLoading);if(typeof successFn==='function'){if(me.getUserLang()===_lang.zht){if(typeof data==='string'){data=me.sim2tra(data);}else if(typeof data==="object"){data=_convertObjectByLang(data,_lang.zht);}}
successFn(data,textStatus,jqXHR);}};option.error=function(objXMLHttpRequest,textStatus,errorThrown){_closeLoadingTip(option.isShowLoading);if(typeof errorFn==='function'){errorFn(objXMLHttpRequest,textStatus,errorThrown);}};return $.ajax(option);},ajaxSubmitForm:function($form,option){if(!$form||$form.length===0){return;}
option=$.extend({'type':"POST",'data':{},'dataType':'json','forceSync':false,'timeout':30000,'isShowLoading':true,'success':function(){},'error':function(){},'complete':function(){}},option);_openLoadingTip(option.isShowLoading);var successFn=option.success,errorFn=option.error;option.success=function(data,textStatus,jqXHR){_closeLoadingTip(option.isShowLoading);if(typeof successFn==='function'){successFn(data,textStatus,jqXHR);}};option.error=function(objXMLHttpRequest,textStatus,errorThrown){_closeLoadingTip(option.isShowLoading);if(typeof errorFn==='function'){errorFn(objXMLHttpRequest,textStatus,errorThrown);}};$form.ajaxSubmit(option);},updateBtnText:function($btn,type){if(!$btn||$btn.length===0){return;}
if(type==='default'){$btn.removeAttr('data-isloading');$btn.html($btn.attr('data-default-text')||'确定');}else if(type==='loading'){$btn.attr('data-isloading','1');$btn.attr('data-default-text',$btn.text());$btn.html('<img src="'+CS.config.env.imgUrlv1+'btn_load.gif" width="24" height="24">');}},checkBtnIsLoading:function($btn){return $btn&&$btn.length>0&&$btn.attr('data-isloading')==='1';},isValidEmailAddress:function(emailAddress){var pattern=new RegExp(/^[+a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i);return pattern.test(emailAddress);},isPhone:function(value){var mobile=/^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;var tel=/^\d{3,4}-?\d{7,9}$/;return(tel.test(value)||mobile.test(value));},isQQ:function(value){var pattern=/^[1-9][0-9]{4,9}$/;return pattern.test(value);},isZipCode:function(value){var pattern=/^[0-9]{6}$/;return pattern.test(value);},isUrl:function(value){var strRegex="^((https|http|ftp|rtsp|mms)?://)"
+"?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?"
+"(([0-9]{1,3}\.){3}[0-9]{1,3}"
+"|"
+"([0-9a-z_!~*'()-]+\.)*"
+"([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\."
+"[a-z]{2,6})"
+"(:[0-9]{1,4})?"
+"((/?)|"
+"(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";var re=new RegExp(strRegex);if(re.test(value)){return(true);}else{return(false);}},getScrollTop:function(){return window.pageYOffset||document.documentElement.scrollTop||document.body.scrollTop;},getScrollLeft:function(){return window.pageXOffset||document.documentElement.scrollLeft||document.body.scrollLeft;},getClientWidth:function(){return(document.compatMode=="CSS1Compat")?document.documentElement.clientWidth:document.body.clientWidth;},getClientHeight:function(){return(document.compatMode=="CSS1Compat")?document.documentElement.clientHeight:document.body.clientHeight;},getScrollWidth:function(){return(document.compatMode=="CSS1Compat")?document.documentElement.scrollWidth:document.body.scrollWidth;},getScrollHeight:function(){return(document.compatMode=="CSS1Compat")?document.documentElement.scrollHeight:document.body.scrollHeight;},isIE:function(){return /msie/.test(navigator.userAgent.toLowerCase());},isIE6:function(){return!!window.ActiveXObject&&!window.XMLHttpRequest;},isMobile:function(){var u=navigator.userAgent;return /AppleWebKit.*Mobile/i.test(u)||/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/.test(u);},encodeHTML:function(str){return str.replace(/ /g,"&nbsp;").replace(/&/g,"&amp;").replace(/>/g,"&gt;").replace(/</g,"&lt;").replace(/\"/g,"&quot;").replace(/\'/g,"&#039;");},decodeHTML:function(str){return str.replace(/&nbsp;/g," ").replace(/&amp;/g,"&").replace(/&gt;/g,">").replace(/&lt;/g,"<").replace(/&quot;/g,'"').replace(/&#039;/g,"'");},isAdult:function(birthday)
{if(!birthday)
{return false;}
var arrBirthday=birthday.split('-');var year=arrBirthday[0];var month=arrBirthday[1];var day=arrBirthday[2];if(!year||!month||!day)
{return false;}
var d=new Date();var cyear=d.getFullYear();var cmonth=d.getMonth()+1;var cday=d.getDate();if(parseInt(cday)<parseInt(day))
{cmonth-=1;}
if(parseInt(cmonth)<parseInt(month))
{cyear-=1;}
if(parseInt(cyear)-parseInt(year)<18)
{return false;}
return true;}};_util.initNameSpace("CS",global);CS.util=_util;})(window,jQuery);;(function($){var _left_separator='<%',_right_separator='%>';function _compileTpl(tpl,data){if(typeof tpl!=='string'||tpl.length===0||typeof data==='undefined'){return'';}
tpl=tpl.replace(/\s*<!\[CDATA\[\s*|\s*\]\]>\s*|[\r\n\t]/g,'');var lineTag='\n',logic_tag='\x1b',isNewEngine=''.trim,arrOut=isNewEngine?["$out='';","$out+=",";","$out;"]:["$out=[];","$out.push(",");","$out.join('');"],arrTpl=tpl.split(_left_separator).join(_right_separator+logic_tag).split(_right_separator),strCode='var ',fn=null;$.each(_helper,function(fnName,fn){strCode+=fnName+'=$helper.'+fnName+','+lineTag;});strCode+=arrOut[0]+lineTag;strCode+='with($data){'+lineTag;for(var i=0,len=arrTpl.length;i<len;i++){var item=arrTpl[i],char0=item.charAt(0);if(char0!==logic_tag){if(char0.length){strCode+=arrOut[1]+'"'+item.replace(/(\\|["'])/g,'\\$1')+'"'+arrOut[2]+lineTag;}}else{if(item.charAt(1)==='='){if(item.charAt(2)==='='){strCode+=arrOut[1]+item.substr(3)+arrOut[2]+lineTag;}else{strCode+=arrOut[1]+'$escapeHTML('+item.substr(2)+')'+arrOut[2]+lineTag;}}else{strCode+=_parseSyntax(item.substr(1))+lineTag;}}}
strCode+='}'+lineTag+'return '+arrOut[3];fn=new Function('$data','$helper',strCode);return fn(data,_helper);}
function _parseSyntax(code,arrOut){code=code.replace(/^\s+/,'');var splitCode=code.split(' '),operator=splitCode.shift(),args=splitCode.join(' ');switch(operator){case'if':code='if('+args+'){';break;case'else':var str_if='';if(splitCode.shift()==='if'){str_if=' if('+splitCode.join(' ')+')';}
code='}else'+str_if+'{';break;case'/if':code='}';break;case'each':var list=splitCode[0]||'$list',as=splitCode[1]||'as',val=splitCode[2]||'$value',index=splitCode[3]||'$index';var param=val+','+index;if(as!=='as'){list='[]';}
code='$each('+list+',function('+param+'){';break;case'/each':code='});';break;default:break;}
return code;}
var _helper={$each:function(data,callback){if(Object.prototype.toString.call(data)==="[object Array]"){for(var i=0,len=data.length;i<len;i++){callback.call(data,data[i],i,data);}}else{for(var j in data){callback.call(data,data[j],j);}}},$escapeHTML:function(str){if(typeof str!=='string'){return str;}
var escapeHTMLRules={"&":"&#38;","<":"&#60;",">":"&#62;",'"':'&#34;',"'":'&#39;',"/":'&#47;'},matchHTML=/&(?!#?\w+;)|<|>|"|'|\//g;return str.replace(matchHTML,function(m){return escapeHTMLRules[m]||m;});}};function _bindEvents(el,event_name,action_name,events,nodes,scope){event_name=event_name.toLowerCase();scope=scope||window;var arr_event=['blur','focus','focusin','focusout','load','resize','scroll','unload','click','dblclick','mousedown','mouseup','mousemove','mouseover','mouseout','mouseenter','mouseleave','change','select','submit','keydown','keypress','keyup','error','contextmenu'];if($.inArray(event_name,arr_event)==-1){return;}
if(events&&typeof events[action_name]==="function"){$(el).on(event_name,function(e){return!!events[action_name].call(scope,e,el,nodes);});}}
function _getRoot(root_id){if(!root_id){return null;}
return typeof root_id==='string'?document.getElementById(root_id):root_id;}
function bindData(tpl,data){return _compileTpl(tpl,data);}
function bindEvent(root_id,events,scope){var root=_getRoot(root_id);if(!root){return null;}
var node={'root':root},attr_nodes=$(root).find("[attr]").toArray(),el=null,str_attr='',arr_attr=null,t=null,arr_bind=[];for(var i=0,len=attr_nodes.length;i<len;i++){el=attr_nodes[i];str_attr=$(el).attr("attr");if(typeof str_attr!="string"||!str_attr){continue;}
arr_attr=str_attr.split(';');for(var j=0,count=arr_attr.length;j<count;j++){t=arr_attr[j].split(":");if(1==t.length&&t[0]){node[t[0]]=el;}
if(2==t.length){if("inner"==t[0]){node[t[1]]=el;}else{arr_bind.push({'el':el,'event':t[0],'action':t[1]});}}}}
if(events){for(var k=0,length=arr_bind.length;k<length;k++){_bindEvents(arr_bind[k].el,arr_bind[k]['event'],arr_bind[k]['action'],events,node,scope);}}
return node;}
function appendHTML(root_id,tpl,data,events,scope){var root=_getRoot(root_id);if(!root){return null;}
data=data||{};var node=null;if(typeof tpl==='string'&&tpl.length){tpl=bindData(tpl,data);$(root).append(tpl);node=bindEvent(root,events,scope);}
return node;}
CS.util.initNameSpace("CS");CS.uiBinder={'bindData':bindData,'bindEvent':bindEvent,'appendHTML':appendHTML};})(jQuery);;(function($){var _util=CS.util;var _defaultConfig={'opacity':0.5,'zIndex':1000000};var _maskCss="maskUI",_win=$(window),_doc=$(document),_iframeSrc=/^https/i.test(window.location.href||'')?'javascript:false':'about:blank';function _setSize($el){if($el){var docWidth=_doc.width(),docHeight=_doc.height();$el.width(docWidth).height(docHeight);}}
function _center($el){var scrollLeft=0,scrollTop=0;if(_util.isIE6()){scrollLeft=_win.scrollLeft();scrollTop=_win.scrollTop();}
$el.css({'left':(_win.width()-$el.width())/2+scrollLeft+'px','top':(_win.height()-$el.height())/2+scrollTop+'px'});}
CS.util.initNameSpace("CS");CS.mask=function(popup,config){this.popup=popup||'<h1>遮罩层</h1>';config=config||{};this.config=$.extend({},_defaultConfig,config);this.popupInfo=null;this.layers={"iframe":null,"shadow":null,"container":null};if(config.zIndex){_defaultConfig.zIndex=config.zIndex;}
return this;};CS.mask.prototype={open:function(){var position=_util.isIE6()?'absolute':'fixed',zIndex=_defaultConfig.zIndex++;if(_util.isIE6()){this.layers.iframe=$('<iframe class="'+_maskCss+'" style="background-color:#000; z-index:'+(zIndex-1)+'; position:absolute; top:0; left:0; background:transparent; border:none; margin:0; padding:0; display:none;" src="'+_iframeSrc+'"></iframe>');}
this.layers.shadow=$("<div>");this.layers.shadow.addClass(_maskCss).css({"zIndex":(zIndex-1),"position":position,"left":0,"top":0,"backgroundColor":"#000","margin":0,"padding":0,"display":"none"});this.layers.container=$("<div>");this.layers.container.addClass(_maskCss).css({"zIndex":zIndex,"position":position,"left":0,"top":0,"display":"none"});if(typeof this.popup!='string'&&(this.popup.parentNode||this.popup.jquery)){var node=this.popup.jquery?this.popup[0]:this.popup;this.popupInfo={'el':node,'parent':node.parentNode,'display':node.style.display,'position':node.style.position};}
$.each(this.layers,function(index,$item){if($item){$(document.body).append($item);}});this.layers.container.append(this.popup);if(this.layers.iframe){_setSize(this.layers.iframe);this.layers.iframe.show();}
_setSize(this.layers.shadow);this.layers.shadow.css({'opacity':this.config.opacity}).show();this.layers.container.show();if(this.popup.jquery||this.popup.nodeType){$(this.popup).show();}
$(':focus').blur();_center(this.layers.container);var me=this;_win.on('resize.mask',function(){_center(me.layers.container);if(me.layers.iframe){_setSize(me.layers.iframe);}
_setSize(me.layers.shadow);});if(_util.isIE6()){_win.on('scroll.mask',function(){_center(me.layers.container);});}},close:function(){$.each(this.layers,function(index,$item){if($item){$item.hide();}});if(this.popupInfo&&this.popupInfo.el){this.popupInfo.el.style.display=this.popupInfo.display;this.popupInfo.el.style.position=this.popupInfo.position;if(this.popupInfo.parent){this.popupInfo.parent.appendChild(this.popupInfo.el);}
this.popupInfo=null;}
$.each(this.layers,function(index,$item){if($item){$item.remove();}});_win.off('.mask');},replacePopup:function(popup){if(popup===this.popup){return;}
if(!this.layers.container){return;}
this.layers.container.hide();if(this.popupInfo&&this.popupInfo.el){this.popupInfo.el.style.display=this.popupInfo.display;this.popupInfo.el.style.position=this.popupInfo.position;if(this.popupInfo.parent){this.popupInfo.parent.appendChild(this.popupInfo.el);}
this.popupInfo=null;}
this.popup=popup;if(typeof this.popup!='string'&&(this.popup.parentNode||this.popup.jquery)){var node=this.popup.jquery?this.popup[0]:this.popup;this.popupInfo={'el':node,'parent':node.parentNode,'display':node.style.display,'position':node.style.position};}
this.layers.container.append(this.popup).show();if(this.popup.jquery||this.popup.nodeType){$(this.popup).show();}
_center(this.layers.container);},center:function(){_center(this.layers.container);}};})(jQuery);(function($){var _util=CS.util,_uiBinder=CS.uiBinder,_mask=CS.mask;var _dialogTpl=['<div attr="inner:popup;" class="popupWrap w380" style="display:none;">','<a attr="click:close;" class="icon closePopup" href="javascript:" title="关闭"></a>','<h3><%=title%></h3>','<div class="popupBody p30">','<div class="popupContent centerTip"><%==content%></div>','<center attr="inner:buttonBox;" class="confirmBtn" style="display:none;"></center>','</div>','</div>'].join('');var _dialogDefaultConfig={"title":'消息提示',"content":'',"leftMsg":'',"closeTime":0,"button":null,"beforeClose":null};var _contentIcon={"error":"alert_error","success":"alert_right","info":"alert_info"};var _remainCloseTimeTips="$time$秒后窗口关闭",_remainLocationTimeTips="$time$秒后页面跳转";var _dialog=function(config){this.config=$.extend({},_dialogDefaultConfig,config);this.nodes=null;this.mask=null;this._closeTimer=null;this._objInterval=null;this._remainTime=0;if(!this.config.button){this.config.button=[];}
this._create();return this;};_dialog.prototype={_create:function(){var me=this;this.nodes=_uiBinder.appendHTML(document.body,_dialogTpl,{"title":this.config.title,"content":this.config.content},{"close":function(){me.close();}});this.addLeftMsg(this.config.leftMsg);this.addButton();this.mask=new _mask(this.nodes.popup);this.mask.open();this.closeByTime();},close:function(){if(this.nodes&&this.nodes.popup){if(this._closeTimer){clearTimeout(this._closeTimer);}
if(this._objInterval){clearInterval(this._objInterval);}
if(typeof this.config.beforeClose==="function"){this.config.beforeClose.call(this);}
this.mask.close();$(this.nodes.popup).remove();}},closeByTime:function(){if(this._closeTimer){clearTimeout(this._closeTimer);}
if(this._objInterval){clearInterval(this._objInterval);}
if(this.config.closeTime>0){var me=this;this._remainTime=this.config.closeTime;this.updateLeftMsg(_remainCloseTimeTips,this._remainTime);this._objInterval=setInterval(function(){me._remainTime--;me.updateLeftMsg(_remainCloseTimeTips,me._remainTime);},1000);this._closeTimer=setTimeout(function(){me.close();},this.config.closeTime*1000);}},updateLeftMsg:function(msg,remainTime){msg=msg.replace("$time$",remainTime);msg=_util.sim2tra(msg);if(this.nodes&&this.nodes.buttonBox){var $leftMsg=$(this.nodes.buttonBox).find("span");if($leftMsg.length>0){$leftMsg.html(msg);}else{this.addLeftMsg(msg);}}},addLeftMsg:function(msg){if(msg){$(this.nodes.buttonBox).prepend('<span class="z xg1">'+msg+'</span>').show();}},addButton:function(){if(this.nodes&&this.nodes.buttonBox&&this.config.button&&this.config.button.length>0){var me=this;$.each(this.config.button,function(index,item){var input=document.createElement("a");input.href='javascript:;';input.className="button";$(input).text(item.value);if(!item.focus){$(input).addClass('white');}
$(input).click(function(){if(typeof item.callback!=='function'||item.callback.call(me)!==false){me.close();}
return false;});me.nodes.buttonBox.appendChild(input);});$(this.nodes.buttonBox).show();}}};CS.util.initNameSpace("CS");CS.dialog={"alert":function(content,callback,noticeType){var title=_util.sim2tra("消息提示");content=_util.sim2tra(content);content='<p>'+content+'</p>';if(!noticeType){noticeType="info";}
return new _dialog({"title":title,"content":content,"button":[{"value":"确定","focus":true}],"beforeClose":callback});},"alertv2":function(content,callback,noticeType,btnvalue){var title=_util.sim2tra("消息提示");content=_util.sim2tra(content);content='<p>'+content+'</p>';if(!noticeType){noticeType="info";}
if(!btnvalue)
{btnvalue="确认"}
return new _dialog({"title":title,"content":content,"button":[{"value":btnvalue,"focus":true}],"beforeClose":callback});},"confirm":function(content,ok,cancel){var title=_util.sim2tra("消息提示");content=_util.sim2tra(content);content='<p>'+content+'</p>';return new _dialog({"title":title,"content":content,"button":[{"value":"确定","focus":true,"callback":ok},{"value":"取消","callback":cancel}]});},"open":function(config){return new _dialog(config);},"close":function(dialog){if(dialog&&typeof dialog.close==="function"){dialog.close();}}};})(jQuery);(function($){var _util=CS.util;function _BrowserType()
{var userAgent=navigator.userAgent;var isOpera=userAgent.indexOf("Opera")>-1;var isIE=userAgent.indexOf("compatible")>-1&&userAgent.indexOf("MSIE")>-1&&!isOpera;var isEdge=userAgent.indexOf("Edge")>-1;var isFF=userAgent.indexOf("Firefox")>-1;var isSafari=userAgent.indexOf("Safari")>-1&&userAgent.indexOf("Chrome")==-1;var isChrome=userAgent.indexOf("Chrome")>-1&&userAgent.indexOf("Safari")>-1;var isIE11=userAgent.indexOf('Trident')>-1&&userAgent.indexOf("rv:11.0")>-1;if(isIE)
{var reIE=new RegExp("MSIE (\\d+\\.\\d+);");reIE.test(userAgent);var fIEVersion=parseFloat(RegExp["$1"]);console.log("IEV:"+fIEVersion);if(fIEVersion==7)
{return"IE7";}
else if(navigator.userAgent.indexOf("MSIE 9.0")>0&&!window.innerWidth)
{return"IE8";}
else if(fIEVersion==9)
{return"IE9";}
else if(fIEVersion==10)
{return"IE10";}
else if(fIEVersion==11)
{return"IE11";}
else
{return"IE6"}}else if(isIE11){return"IE11";}
if(isFF){return"Firefox";}
if(isOpera){return"Opera";}
if(isSafari){return"Safari";}
if(isChrome){return"Chrome";}
if(isEdge){return"Edge";}
return'unknown';}
function _detectOS(){var sUserAgent=navigator.userAgent;var isWin=(navigator.platform=="Win32")||(navigator.platform=="Windows");var isMac=(navigator.platform=="Mac68K")||(navigator.platform=="MacPPC")||(navigator.platform=="Macintosh")||(navigator.platform=="MacIntel");if(isMac)return"Mac";var isUnix=(navigator.platform=="X11")&&!isWin&&!isMac;if(isUnix)return"Unix";var isLinux=(String(navigator.platform).indexOf("Linux")>-1);if(isLinux)return"Linux";if(isWin){var isWin2K=sUserAgent.indexOf("Windows NT 5.0")>-1||sUserAgent.indexOf("Windows 2000")>-1;if(isWin2K)return"Win2000";var isWinXP=sUserAgent.indexOf("Windows NT 5.1")>-1||sUserAgent.indexOf("Windows XP")>-1;if(isWinXP)return"WinXP";var isWin2003=sUserAgent.indexOf("Windows NT 5.2")>-1||sUserAgent.indexOf("Windows 2003")>-1;if(isWin2003)return"Win2003";var isWinVista=sUserAgent.indexOf("Windows NT 6.0")>-1||sUserAgent.indexOf("Windows Vista")>-1;if(isWinVista)return"WinVista";var isWin7=sUserAgent.indexOf("Windows NT 6.1")>-1||sUserAgent.indexOf("Windows 7")>-1;if(isWin7)return"Win7";}
return"other";}
_util.initNameSpace("CS");CS.browser={'browserType':_BrowserType,'detectOS':_detectOS};})(jQuery);(function(root,factory){if(typeof define==="function"&&define.amd){define(function(){return(root.Tucao=factory());});}else if(typeof module==="object"&&module.exports){module.exports=(root.Tucao=factory());}else{root.Tucao=factory();}}(this,function(){var Tucao=(function(){var request=function(productId,data){var form=document.createElement("form");form.id="form";form.name="form";document.body.appendChild(form);for(key in data){var input=document.createElement("input");input.type="text";input.name=key;input.value=data[key];form.appendChild(input);}
form.method="POST";form.action="https://support.qq.com/product/"+productId;form.submit();document.body.removeChild(form);}
return{request:request}})();return Tucao;}));