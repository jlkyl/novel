!function (e, t) {
    function r(e) {
        return function (t) {
            return {}.toString.call(t) == "[object " + e + "]"
        }
    }

    function n() {
        return U++
    }

    function i(e, t, r) {
        r = r || this;
        for (var n = 0, i = e.length; n < i; n++) "undefined" != typeof e[n] && t.call(r, e[n], n, e)
    }

    function a(e) {
        return e.match(R)[0]
    }

    function o(e) {
        for (e = (e = e.replace(G, "/")).replace(k, "$1/"); e.match($);) e = e.replace($, "/");
        return e
    }

    function s(e) {
        var t = e.length - 1, r = e.charAt(t);
        return "#" === r ? e.substring(0, t) : ".css" === e.substring(t - 3) ? e : ".js" === e.substring(t - 2) || e.indexOf("?") > 0 || "/" === r ? e : e + ".js"
    }

    function u(e) {
        var t = w.alias;
        return t && F(t[e]) ? t[e] : e
    }

    function c(e) {
        var t;
        if (e.indexOf(".") > -1 && (t = M.exec(e))) {
            var r;
            (r = t[1]) && (e = e.substring(0, e.lastIndexOf(r))), r = ".js" + (r || ""), e = e.split(".").join("/") + r
        }
        return e
    }

    function f(e) {
        var t, r = w.paths;
        return r && (t = e.match(X)) && F(r[t[1]]) && (e = r[t[1]] + t[2]), e
    }

    function l(e) {
        var t = w.vars;
        return t && e.indexOf("{") > -1 && (e = e.replace(H, function (e, r) {
            return F(t[r]) ? t[r] : e
        })), e
    }

    function v(e) {
        var t = w.map, r = e;
        if (t) for (var n = 0, i = t.length; n < i; n++) {
            var a = t[n];
            if ((r = q(a) ? a(e) || e : e.replace(a[0], a[1])) !== e) break
        }
        return r
    }

    function d(e, t) {
        var r, n = e.charAt(0);
        if (Q.test(e)) r = e; else if ("." === n) r = o((t ? a(t) : w.cwd) + e); else if ("/" === n) {
            var i = w.cwd.match(V);
            r = i ? i[0] + e.substring(1) : e
        } else r = w.base + e;
        return 0 === r.indexOf("//") && (r = location.protocol + r), r
    }

    function h(e, t, r) {
        var n = te.test(e), i = J.createElement(n ? "link" : "script");
        if (r) {
            var a = q(r) ? r(e) : r;
            a && (i.charset = a)
        }
        return g(i, t, n, e), n ? (i.rel = "stylesheet", i.href = e) : (i.async = !0, i.src = e), Y = i, ee ? Z.insertBefore(i, ee) : Z.appendChild(i), Y = null, i
    }

    function g(e, t, r, n) {
        function i() {
            e.onload = e.onerror = e.onreadystatechange = null, r || w.debug || Z.removeChild(e), e = null, t()
        }

        var a = "onload" in e;
        !r || !re && a ? a ? (e.onload = i, e.onerror = function () {
            j("error", {uri: n, node: e}), i()
        }) : e.onreadystatechange = function () {
            /loaded|complete/.test(e.readyState) && i()
        } : setTimeout(function () {
            p(e, t)
        }, 1)
    }

    function p(e, t) {
        var r, n = e.sheet;
        if (re) n && (r = !0); else if (n) try {
            n.cssRules && (r = !0)
        } catch (i) {
            "NS_ERROR_DOM_SECURITY_ERR" === i.name && (r = !0)
        }
        setTimeout(function () {
            r ? t() : p(e, t)
        }, 20)
    }

    function m() {
        if (Y) return Y;
        if (z && "interactive" === z.readyState) return z;
        for (var e = Z.getElementsByTagName("script"), t = e.length - 1; t >= 0; t--) {
            var r = e[t];
            if ("interactive" === r.readyState) return z = r
        }
    }

    function b(e) {
        var t = oe.exec(e), r = ie;
        if (!t) return [];
        "require" !== (t = t[1]) && (r = (r = r.toString().replace(/require/g, t)).slice(1, r.length - 2), r = new RegExp(r, "g"));
        var n = [];
        return e.replace(ae, "").replace(r, function (e, t, r) {
            r && n.push(r)
        }), n
    }

    function y(e, t) {
        this.uri = e, this.dependencies = t || [], this.exports = null, this.status = 0, this._waitings = {}, this._remain = 0
    }

    function E(e) {
        var t = w.combo;
        switch (typeof t) {
            case"string":
                return -1 === e.indexOf(t);
            default:
                return !1
        }
    }

    function x(e) {
        var t = [], r = ye.exec(e[0])[1], n = r.length;
        i(e, function (e) {
            t.push(e.substr(n))
        }), S(r, t)
    }

    function S(e, t) {
        for (var r = [], n = 0, i = t.length; n < i; n++) r[n] = t[n].replace(/\?.*$/, "");
        var a = e + me[0] + r.join(me[1]);
        he && (a += he);
        var o = t.length > be;
        if (t.length > 1 && o) {
            var s = O(t, be);
            S(e, s[0]), S(e, s[1])
        } else {
            if (o) throw new Error("The combo url is too long: " + a);
            for (var n = 0, i = t.length; n < i; n++) pe[e + t[n]] = a
        }
    }

    function O(e, t) {
        for (var r = 0, n = e.length; r < n; r++) if (r > t - 1) return [e.splice(0, r), e]
    }

    function _(e) {
        return Ee.test(e)
    }

    function A(e) {
        if (de) return de.test ? de.test(e) : de(e)
    }

    function L(e) {
        var t = w.comboSyntax || me, r = t[0], n = t[1];
        return r && e.indexOf(r) > 0 || n && e.indexOf(n) > 0
    }

    if (e.LBF) var T = e.LBF;
    var exports = e.LBF = {version: "2.0.0"}, w = exports.data = {};
    exports.noConflict = function () {
        T && (e.LBF = T)
    };
    var N = r("Object"), F = r("String"), B = Array.isArray || r("Array"), q = r("Function"), D = r("Number"),
        C = r("RegExp"), U = 0, I = w.events = {};
    exports.on = function (e, t) {
        return (I[e] || (I[e] = [])).push(t), exports
    }, exports.off = function (e, t) {
        if (!e && !t) return I = w.events = {}, exports;
        var r = I[e];
        if (r) if (t) for (var n = r.length - 1; n >= 0; n--) r[n] === t && r.splice(n, 1); else delete I[e];
        return exports
    };
    var j = exports.emit = function (e, t) {
            var r = I[e];
            if (r) for (var n = 0, i = (r = r.slice()).length; n < i; n++) r[n](t);
            return exports
        }, R = /[^?#]*\//, G = /\/\.\//g, $ = /\/[^/]+\/\.\.\//, k = /([^:/])\/+\//g, X = /^([^/:]+)(\/.+)$/,
        H = /{([^{]+)}/g, M = /^[\w-_]*(?:\.[\w-_]+)*(\?[\w-_&=]*)?$/, Q = /^\/\/.|:\//, V = /^.*?\/\/.*?\//,
        J = document, K = location.href && 0 !== location.href.indexOf("about:") ? a(location.href) : "", P = J.scripts,
        W = a(function (e) {
            return e.hasAttribute ? e.src : e.getAttribute("src", 4)
        }(J.getElementById("LBFnode") || P[P.length - 1]) || K);
    exports.resolve = function (e, t) {
        if (!e) return "";
        var r = d(e = s(e = l(e = f(e = u(e = c(e = u(e)))))), t);
        return r = v(r)
    };
    var Y, z, Z = J.head || J.getElementsByTagName("head")[0] || J.documentElement,
        ee = Z.getElementsByTagName("base")[0], te = /\.css(?:\?|$)/i,
        re = +navigator.userAgent.replace(/.*(?:AppleWebKit|AndroidWebKit)\/(\d+).*/, "$1") < 536;
    exports.request = h;
    var ne,
        ie = /"(?:\\"|[^"])*"|'(?:\\'|[^'])*'|\/\*[\S\s]*?\*\/|\/(?:\\\/|[^/\r\n])+\/(?=[^\/])|\/\/.*|\.\s*require|(?:^|[^$])\brequire\s*\(\s*(["'])(.+?)\1\s*\)/g,
        ae = /\\\\/g, oe = /^function[\s]*\([\s]*([^\s,\)]+)/, se = exports.cache = {}, ue = {}, ce = {}, fe = {},
        le = y.STATUS = {FETCHING: 1, SAVED: 2, LOADING: 3, LOADED: 4, EXECUTING: 5, EXECUTED: 6};
    y.prototype.resolve = function () {
        for (var e = this, t = e.dependencies, r = [], n = w.ignoreCss, i = 0, a = t.length; i < a; i++) n && -1 !== t[i].indexOf(".css") && (!0 === n || B(n) && -1 !== inArray(e.id, n)) || r.push(y.resolve(t[i], e.uri));
        return r
    }, y.prototype.load = function () {
        var e = this;
        if (!(e.status >= le.LOADING)) {
            e.status = le.LOADING;
            var t = e.resolve();
            j("beforeload", t), j("load", t);
            for (var r, n = e._remain = t.length, i = 0; i < n; i++) (r = y.get(t[i])).status < le.LOADED ? r._waitings[e.uri] = (r._waitings[e.uri] || 0) + 1 : e._remain--;
            if (0 !== e._remain) {
                var a = {};
                for (i = 0; i < n; i++) (r = se[t[i]]).status < le.FETCHING ? r.fetch(a) : r.status === le.SAVED && r.load();
                for (var o in a) a.hasOwnProperty(o) && a[o]()
            } else e.onload()
        }
    }, y.prototype.onload = function () {
        var e = this;
        e.status = le.LOADED, e.callback && e.callback();
        var t, r, n = e._waitings;
        for (t in n) n.hasOwnProperty(t) && ((r = se[t])._remain -= n[t], 0 === r._remain && r.onload());
        delete e._waitings, delete e._remain
    }, y.prototype.fetch = function (e) {
        function t() {
            exports.request(i.requestUri, i.onRequest, i.charset)
        }

        var r = this, n = r.uri;
        r.status = le.FETCHING;
        var i = {uri: n};
        j("fetch", i);
        var a = i.requestUri || n;
        a && !ce[a] ? ue[a] ? fe[a].push(r) : (ue[a] = !0, fe[a] = [r], j("request", i = {
            uri: n,
            requestUri: a,
            onRequest: function () {
                delete ue[a], ce[a] = !0, ne && (y.save(n, ne), ne = null);
                var e, t = fe[a];
                for (delete fe[a]; e = t.shift();) e.load()
            },
            charset: w.charset
        }), i.requested || (e ? e[i.requestUri] = t : t())) : r.load()
    }, y.prototype.exec = function () {
        function require(e) {
            return y.get(require.resolve(e)).exec()
        }

        var e = this;
        if (e.status >= le.EXECUTING) return e.exports;
        e.status = le.EXECUTING;
        var t = e.uri;
        require.resolve = function (e) {
            return y.resolve(e, t)
        }, require.async = function (e, r) {
            return y.use(e, r, t + "_async_" + n()), require
        };
        var r = e.factory, exports = q(r) ? r(require, e.exports = {}, e) : r;
        return void 0 === exports && (exports = e.exports), delete e.factory, e.exports = exports, e.status = le.EXECUTED, j("exec", e), exports
    }, y.resolve = function (e, t) {
        var r = {id: e, refUri: t};
        return j("resolve", r), r.uri || exports.resolve(r.id, t)
    }, y.define = function (e, t, r) {
        var n = arguments.length;
        1 === n ? (r = e, e = void 0) : 2 === n && (r = t, B(e) ? (t = e, e = void 0) : t = void 0), !B(t) && q(r) && (t = b(r.toString()));
        var i = {id: e, uri: y.resolve(e), deps: t, factory: r};
        if (!i.uri && J.attachEvent) {
            var a = m();
            a && (i.uri = a.src)
        }
        j("define", i), i.uri ? y.save(i.uri, i) : ne = i
    }, y.save = function (e, t) {
        var r = y.get(e);
        r.status < le.SAVED && (r.id = t.id || e, r.dependencies = t.deps || [], r.factory = t.factory, r.status = le.SAVED, j("save", r))
    }, y.get = function (e, t) {
        return se[e] || (se[e] = new y(e, t))
    }, y.use = function (t, r, n) {
        var t = function (e) {
            var e = B(e) ? e : [e], t = {}, r = [], n = 0, i = w.deps;
            for (n = 0; n < e.length; n++) if (!t[e[n]]) {
                t[e[n]] = !0, r.push(e[n]);
                for (var a = i[e[n]] || [], o = 0; o < a.length; o++) e.push(a[o])
            }
            return r
        }(t), i = y.get(n, B(t) ? t : [t]);
        i.callback = function () {
            for (var exports = [], t = i.resolve(), n = 0, a = t.length; n < a; n++) exports[n] = se[t[n]].exec();
            r && r.apply(e, exports), delete i.callback
        }, i.load()
    }, y.preload = function (e) {
        var t = w.preload, r = t.length;
        r ? y.use(t, function () {
            t.splice(0, r), y.preload(e)
        }, w.cwd + "_preload_" + n()) : e()
    }, exports.use = function (e, t) {
        return y.preload(function () {
            y.use(e, t, w.cwd + "_use_" + n())
        }), exports
    }, y.define.cmd = {}, e.define = exports.define = y.define, exports.Module = y, w.fetchedList = ce, w.cid = n, exports.require = function (e) {
        var t = y.get(y.resolve(e));
        return t.status < le.EXECUTING && (t.onload(), t.exec()), t.exports
    }, w.base = W, w.dir = W, w.cwd = K, w.charset = "utf-8", w.preload = [], w.deps = {}, exports.config = function (e) {
        for (var t in e) {
            var r = e[t], n = w[t];
            if (n && N(n)) for (var i in r) n[i] = r[i]; else B(n) ? r = n.concat(r) : "base" === t && ("/" !== r.slice(-1) && (r += "/"), r = d(r)), w[t] = r
        }
        return j("config", e), exports
    };
    var ve = [["globalSettings", exports.data], ["lang.forEach", i], ["lang.isType", r], ["lang.isObject", N], ["lang.isString", F], ["lang.isArray", B], ["lang.isFunction", q], ["lang.isNumber", D], ["lang.isRegExp", C], ["util.request", h]];
    e.JSON && ve.push(["lang.JSON", e.JSON]), e.jQuery && 0 === (e.jQuery.version || "").indexOf("1.7") && ve.push(["lib.jQuery", e.jQuery]), i(ve, function (e) {
        exports.define(e[0], function (require, exports, module) {
            module.exports = e[1]
        })
    });
    var de, he, ge = (y = LBF.Module).STATUS.FETCHING, pe = (w = LBF.data).comboHash = {}, me = ["c/=/", ",/"], be = 20;
    LBF.on("load", function (e) {
        var t = e.length;
        if (!(t < 2) && w.combo) {
            w.comboSyntax && (me = w.comboSyntax), w.comboMaxLength && (be = w.comboMaxLength), w.comboSuffix && (he = w.comboSuffix), de = w.comboExcludes;
            for (var r = [], n = 0; n < t; n++) {
                var i = e[n];
                pe[i] || E(i) || y.get(i).status < ge && !_(i) && !A(i) && !L(i) && r.push(i)
            }
            r.length > 1 && x(r)
        }
    }), LBF.on("fetch", function (e) {
        w.combo && (e.requestUri = pe[e.uri] || e.uri)
    });
    var ye = /^(\S+:\/{2,3}[^\/]+\/)/, Ee = /\.css(?:\?.*)?$/;
    if (w.test) {
        var xe = LBF.test || (LBF.test = {});
        xe.uris2paths = x, xe.paths2hash = paths2hash
    }
    LBF.config({
        alias: {globalSettings: W + "globalSettings"},
        vars: {theme: W + "ui/themes/default"},
        paths: {
            app: W + "app",
            lang: W + "lang",
            monitor: W + "monitor",
            lib: W + "lib",
            ui: W + "ui",
            util: W + "util",
            format: W + "format"
        }
    }), LBF.config({})
}(this);
/*! LBF 1.0.2 2016-02-26 */
(function(global, undefined) {

// Avoid conflicting when `LBF.js` is loaded multiple times
if (global.LBF) {
    var lastVersion = global.LBF;
}

var exports = global.LBF = {
  // The current version of Sea.js being used
  version: "1.0.2"
}

var data = exports.data = {}

exports.noConflict = function(){
	lastVersion && (global.LBF = lastVersion);
}
function isType(type) {
  return function(obj) {
    return {}.toString.call(obj) == "[object " + type + "]"
  }
}

var isObject = isType("Object")
var isString = isType("String")
var isArray = Array.isArray || isType("Array")
var isFunction = isType("Function")
var isNumber = isType("Number")
var isRegExp = isType("RegExp")

var _cid = 0
function cid() {
  return _cid++
}

function forEach(arr, cb, context){
    context = context || this;

    for(var i= 0, len= arr.length; i< len; i++){
        if(typeof arr[i] !== 'undefined'){
            cb.call(context, arr[i], i, arr);
        }
    }
}

var events = data.events = {}

// Bind event
exports.on = function(name, callback) {
  var list = events[name] || (events[name] = [])
  list.push(callback)
  return exports
}

// Remove event. If `callback` is undefined, remove all callbacks for the
// event. If `event` and `callback` are both undefined, remove all callbacks
// for all events
exports.off = function(name, callback) {
  // Remove *all* events
  if (!(name || callback)) {
    events = data.events = {}
    return exports
  }

  var list = events[name]
  if (list) {
    if (callback) {
      for (var i = list.length - 1; i >= 0; i--) {
        if (list[i] === callback) {
          list.splice(i, 1)
        }
      }
    }
    else {
      delete events[name]
    }
  }

  return exports
}

// Emit event, firing all bound callbacks. Callbacks receive the same
// arguments as `emit` does, apart from the event name
var emit = exports.emit = function(name, data) {
  var list = events[name], fn

  if (list) {
    // Copy callback lists to prevent modification
    list = list.slice()

    // Execute event callbacks, use index because it's the faster.
    for(var i = 0, len = list.length; i < len; i++) {
      list[i](data)
    }
  }

  return exports
}


var DIRNAME_RE = /[^?#]*\//

var DOT_RE = /\/\.\//g
var DOUBLE_DOT_RE = /\/[^/]+\/\.\.\//
var MULTI_SLASH_RE = /([^:/])\/+\//g
var EXT = /(?:js|css|less|php)$/

// Extract the directory portion of a path
// dirname("a/b/c.js?t=123#xx/zz") ==> "a/b/"
// ref: http://jsperf.com/regex-vs-split/2
function dirname(path) {
  return path.match(DIRNAME_RE)[0]
}

// Canonicalize a path
// realpath("http://test.com/a//./b/../c") ==> "http://test.com/a/c"
function realpath(path) {
  // /a/b/./c/./d ==> /a/b/c/d
  path = path.replace(DOT_RE, "/")

  /*
    @author wh1100717
    a//b/c ==> a/b/c
    a///b/////c ==> a/b/c
    DOUBLE_DOT_RE matches a/b/c//../d path correctly only if replace // with / first
  */
  path = path.replace(MULTI_SLASH_RE, "$1/")

  // a/b/c/../../d  ==>  a/b/../d  ==>  a/d
  while (path.match(DOUBLE_DOT_RE)) {
    path = path.replace(DOUBLE_DOT_RE, "/")
  }

  return path
}

// Normalize an id
// normalize("path/to/a") ==> "path/to/a.js"
// NOTICE: substring is faster than negative slice and RegExp
function normalize(path) {
  var last = path.length - 1
  var lastC = path.charAt(last)

  // If the uri ends with `#`, just return it without '#'
  if (lastC === "#") {
    return path.substring(0, last)
  }

  // ignore file with css ext
  if(path.substring(last - 3) === '.css'){
    return path;
  }

  // add js file ext
  return (path.substring(last - 2) === ".js" ||
      path.indexOf("?") > 0 ||
      lastC === "/") ? path : path + ".js"
}


var PATHS_RE = /^([^/:]+)(\/.+)$/
var VARS_RE = /{([^{]+)}/g
var NAMESPACE_RE = /^[\w-_]*(?:\.[\w-_]+)*(\?[\w-_&=]*)?$/


function parseAlias(id) {
  var alias = data.alias
  return alias && isString(alias[id]) ? alias[id] : id
}

// a.b.c -> a/b/c.js
function parseNamespace(id) {
    var match;
    // no dot or uri with static file extension
    // eg. require('jQuery') // alias for 'lib.jQuery'
    // eg. ./mod.js // relative path
    if(id.indexOf('.') > -1 && (match = NAMESPACE_RE.exec(id))){
        var ext;

        // id = 'a.b.c?v=123&t=1'
        // ->
        // ext = '?v=123&t=1'
        if(ext = match[1]){
            // remove ext from id
            id = id.substring(0, id.lastIndexOf(ext));
        }

        ext = '.js' + (ext || '');

        // replace all '.' to '/'
        id = id.split('.').join('/') + ext;
    }

    return id
}

function parsePaths(id) {
  var paths = data.paths
  var m

  if (paths && (m = id.match(PATHS_RE)) && isString(paths[m[1]])) {
    id = paths[m[1]] + m[2]
  }

  return id
}

function parseVars(id) {
  var vars = data.vars

  if (vars && id.indexOf("{") > -1) {
    id = id.replace(VARS_RE, function(m, key) {
      return isString(vars[key]) ? vars[key] : m
    })
  }

  return id
}

function parseMap(uri) {
  var map = data.map
  var ret = uri

  if (map) {
    for (var i = 0, len = map.length; i < len; i++) {
      var rule = map[i]

      ret = isFunction(rule) ?
          (rule(uri) || uri) :
          uri.replace(rule[0], rule[1])

      // Only apply the first matched rule
      if (ret !== uri) break
    }
  }

  return ret
}


var ABSOLUTE_RE = /^\/\/.|:\//
var ROOT_DIR_RE = /^.*?\/\/.*?\//

function addBase(id, refUri) {
  var ret
  var first = id.charAt(0)

  // Absolute
  if (ABSOLUTE_RE.test(id)) {
    ret = id
  }
  // Relative
  else if (first === ".") {
    ret = realpath((refUri ? dirname(refUri) : data.cwd) + id)
  }
  // Root
  else if (first === "/") {
    var m = data.cwd.match(ROOT_DIR_RE)
    ret = m ? m[0] + id.substring(1) : id
  }
  // Top-level
  else {
    ret = data.base + id
  }

  // Add default protocol when uri begins with "//"
  if (ret.indexOf("//") === 0) {
    ret = location.protocol + ret
  }

  return ret
}

function id2Uri(id, refUri) {
  if (!id) return ""

  id = parseAlias(id)
  id = parseNamespace(id)
  id = parseAlias(id)
  id = parsePaths(id)
  id = parseVars(id)
  id = normalize(id)

  var uri = addBase(id, refUri)
  uri = parseMap(uri)

  return uri
}


var doc = document
var cwd = (!location.href || location.href.indexOf('about:') === 0) ? '' : dirname(location.href)
var scripts = doc.scripts

// Recommend to add `LBFnode` id for the `LBF.js` script element
var loaderScript = doc.getElementById("LBFnode") ||
    scripts[scripts.length - 1]

// When `LBF.js` is inline, set loaderDir to current working directory
var loaderDir = dirname(getScriptAbsoluteSrc(loaderScript) || cwd)

function getScriptAbsoluteSrc(node) {
  return node.hasAttribute ? // non-IE6/7
      node.src :
    // see http://msdn.microsoft.com/en-us/library/ms536429(VS.85).aspx
      node.getAttribute("src", 4)
}


// For Developers
exports.resolve = id2Uri


var head = doc.head || doc.getElementsByTagName("head")[0] || doc.documentElement
var baseElement = head.getElementsByTagName("base")[0]

var IS_CSS_RE = /\.css(?:\?|$)/i
var currentlyAddingScript
var interactiveScript

// `onload` event is not supported in WebKit < 535.23 and Firefox < 9.0
// ref:
//  - https://bugs.webkit.org/show_activity.cgi?id=38995
//  - https://bugzilla.mozilla.org/show_bug.cgi?id=185236
//  - https://developer.mozilla.org/en/HTML/Element/link#Stylesheet_load_events
var isOldWebKit = +navigator.userAgent
  .replace(/.*(?:AppleWebKit|AndroidWebKit)\/(\d+).*/, "$1") < 536


function request(url, callback, charset) {
  var isCSS = IS_CSS_RE.test(url)
  var node = doc.createElement(isCSS ? "link" : "script")

  if (charset) {
    var cs = isFunction(charset) ? charset(url) : charset
    if (cs) {
      node.charset = cs
    }
  }

  addOnload(node, callback, isCSS, url)

  if (isCSS) {
    node.rel = "stylesheet"
    node.href = url
  }
  else {
    node.async = true
    node.src = url
  }

  // For some cache cases in IE 6-8, the script executes IMMEDIATELY after
  // the end of the insert execution, so use `currentlyAddingScript` to
  // hold current node, for deriving url in `define` call
  currentlyAddingScript = node

  // ref: #185 & http://dev.jquery.com/ticket/2709
  baseElement ?
    head.insertBefore(node, baseElement) :
    head.appendChild(node)

  currentlyAddingScript = null

  return node

}

function addOnload(node, callback, isCSS, url) {
  var supportOnload = "onload" in node

  // for Old WebKit and Old Firefox
  if (isCSS && (isOldWebKit || !supportOnload)) {
    setTimeout(function() {
      pollCss(node, callback)
    }, 1) // Begin after node insertion
    return
  }

  if (supportOnload) {
    node.onload = onload
    node.onerror = function() {
      emit("error", { uri: url, node: node })
      onload()
    }
  }
  else {
    node.onreadystatechange = function() {
      if (/loaded|complete/.test(node.readyState)) {
        onload()
      }
    }
  }

  function onload() {
    // Ensure only run once and handle memory leak in IE
    node.onload = node.onerror = node.onreadystatechange = null

    // Remove the script to reduce memory leak
    if (!isCSS && !data.debug) {
      head.removeChild(node)
    }

    // Dereference the node
    node = null

    callback()
  }
}

function pollCss(node, callback) {
  var sheet = node.sheet
  var isLoaded

  // for WebKit < 536
  if (isOldWebKit) {
    if (sheet) {
      isLoaded = true
    }
  }
  // for Firefox < 9.0
  else if (sheet) {
    try {
      if (sheet.cssRules) {
        isLoaded = true
      }
    } catch (ex) {
      // The value of `ex.name` is changed from "NS_ERROR_DOM_SECURITY_ERR"
      // to "SecurityError" since Firefox 13.0. But Firefox is less than 9.0
      // in here, So it is ok to just rely on "NS_ERROR_DOM_SECURITY_ERR"
      if (ex.name === "NS_ERROR_DOM_SECURITY_ERR") {
        isLoaded = true
      }
    }
  }

  setTimeout(function() {
    if (isLoaded) {
      // Place callback here to give time for style rendering
      callback()
    }
    else {
      pollCss(node, callback)
    }
  }, 20)
}

function getCurrentScript() {
  if (currentlyAddingScript) {
    return currentlyAddingScript
  }

  // For IE6-9 browsers, the script onload event may not fire right
  // after the script is evaluated. Kris Zyp found that it
  // could query the script nodes and the one that is in "interactive"
  // mode indicates the current script
  // ref: http://goo.gl/JHfFW
  if (interactiveScript && interactiveScript.readyState === "interactive") {
    return interactiveScript
  }

  var scripts = head.getElementsByTagName("script")

  for (var i = scripts.length - 1; i >= 0; i--) {
    var script = scripts[i]
    if (script.readyState === "interactive") {
      interactiveScript = script
      return interactiveScript
    }
  }
}


// For Developers
exports.request = request

var REQUIRE_RE = /"(?:\\"|[^"])*"|'(?:\\'|[^'])*'|\/\*[\S\s]*?\*\/|\/(?:\\\/|[^/\r\n])+\/(?=[^\/])|\/\/.*|\.\s*require|(?:^|[^$])\brequire\s*\(\s*(["'])(.+?)\1\s*\)/g,
    SLASH_RE = /\\\\/g,
    REQUIRE_NAME_RE = /^function[\s]*\([\s]*([^\s,\)]+)/;

function parseDependencies(code) {
    // get require function name
    // in compress code, require function name is no longer 'require'
    var requireName = REQUIRE_NAME_RE.exec(code),
        RE = REQUIRE_RE;

    // no dependencies
    if( !requireName ){
        return [];
    }

    if((requireName = requireName[1]) !== 'require'){
        // reconstruct require regexp
        RE = RE
                .toString()
                // for compressed code
                // replace arg 'require' with actual name
                .replace(/require/g, requireName);

        // remove head & tail
        // '/xxxxx/g' -> 'xxxxx'
        RE = RE.slice(1, RE.length - 2);

        RE = new RegExp(RE, 'g');
    }

    // grep deps by using regexp match
    var ret = [];

    code.replace(SLASH_RE, '')
        .replace(RE, function(m, m1, m2) {
            m2 && ret.push(m2);
        });

    return ret;
}

var cachedMods = exports.cache = {}
var anonymousMeta

var fetchingList = {}
var fetchedList = {}
var callbackList = {}

var STATUS = Module.STATUS = {
  // 1 - The `module.uri` is being fetched
  FETCHING: 1,
  // 2 - The meta data has been saved to cachedMods
  SAVED: 2,
  // 3 - The `module.dependencies` are being loaded
  LOADING: 3,
  // 4 - The module are ready to execute
  LOADED: 4,
  // 5 - The module is being executed
  EXECUTING: 5,
  // 6 - The `module.exports` is available
  EXECUTED: 6
}


function Module(uri, deps) {
  this.uri = uri
  this.dependencies = deps || []
  this.exports = null
  this.status = 0

  // Who depends on me
  this._waitings = {}

  // The number of unloaded dependencies
  this._remain = 0
}

// Resolve module.dependencies
Module.prototype.resolve = function() {
  var mod = this
  var ids = mod.dependencies
  var uris = []
  var ignoreCss = data.ignoreCss

  for (var i = 0, len = ids.length; i < len; i++) {
  	//增加取消载入模块所依赖css的功能
  	//ignoreCss可以是Boolean或数组
  	if(ignoreCss && ids[i].indexOf('.css') !== -1) {
  		if(ignoreCss === true || isArray(ignoreCss) && inArray(mod.id, ignoreCss) !== -1) {
  			continue;
  		}
  	}

    uris.push(Module.resolve(ids[i], mod.uri))
  }
  return uris
}

// Load module.dependencies and fire onload when all done
Module.prototype.load = function() {
  var mod = this

  // If the module is being loaded, just wait it onload call
  if (mod.status >= STATUS.LOADING) {
    return
  }

  mod.status = STATUS.LOADING

  // Emit `load` event for plugins such as combo plugin
  var uris = mod.resolve()

  emit("beforeload", uris);

  emit("load", uris)

  var len = mod._remain = uris.length
  var m

  // Initialize modules and register waitings
  for (var i = 0; i < len; i++) {
    m = Module.get(uris[i])

    if (m.status < STATUS.LOADED) {
      // Maybe duplicate: When module has dupliate dependency, it should be it's count, not 1
      m._waitings[mod.uri] = (m._waitings[mod.uri] || 0) + 1
    }
    else {
      mod._remain--
    }
  }

  if (mod._remain === 0) {
    mod.onload()
    return
  }

  // Begin parallel loading
  var requestCache = {}

  for (i = 0; i < len; i++) {
    m = cachedMods[uris[i]]

    if (m.status < STATUS.FETCHING) {
      m.fetch(requestCache)
    }
    else if (m.status === STATUS.SAVED) {
      m.load()
    }
  }

  // Send all requests at last to avoid cache bug in IE6-9. Issues#808
  for (var requestUri in requestCache) {
    if (requestCache.hasOwnProperty(requestUri)) {
      requestCache[requestUri]()
    }
  }
}

// Call this method when module is loaded
Module.prototype.onload = function() {
  var mod = this
  mod.status = STATUS.LOADED

  if (mod.callback) {
    mod.callback()
  }

  // Notify waiting modules to fire onload
  var waitings = mod._waitings
  var uri, m

  for (uri in waitings) {
    if (waitings.hasOwnProperty(uri)) {
      m = cachedMods[uri]
      m._remain -= waitings[uri]
      if (m._remain === 0) {
        m.onload()
      }
    }
  }

  // Reduce memory taken
  delete mod._waitings
  delete mod._remain
}

// Fetch a module
Module.prototype.fetch = function(requestCache) {
  var mod = this
  var uri = mod.uri

  mod.status = STATUS.FETCHING

  // Emit `fetch` event for plugins such as combo plugin
  var emitData = { uri: uri }
  emit("fetch", emitData)
  var requestUri = emitData.requestUri || uri

  // Empty uri or a non-CMD module
  if (!requestUri || fetchedList[requestUri]) {
    mod.load()
    return
  }

  if (fetchingList[requestUri]) {
    callbackList[requestUri].push(mod)
    return
  }

  fetchingList[requestUri] = true
  callbackList[requestUri] = [mod]

  // Emit `request` event for plugins such as text plugin
  emit("request", emitData = {
    uri: uri,
    requestUri: requestUri,
    onRequest: onRequest,
    charset: data.charset
  })

  if (!emitData.requested) {
    requestCache ?
        requestCache[emitData.requestUri] = sendRequest :
        sendRequest()
  }

  function sendRequest() {
    exports.request(emitData.requestUri, emitData.onRequest, emitData.charset)
  }

  function onRequest() {
    delete fetchingList[requestUri]
    fetchedList[requestUri] = true

    // Save meta data of anonymous module
    if (anonymousMeta) {
      Module.save(uri, anonymousMeta)
      anonymousMeta = null
    }

    // Call callbacks
    var m, mods = callbackList[requestUri]
    delete callbackList[requestUri]
    while ((m = mods.shift())) m.load()
  }
}

// Execute a module
Module.prototype.exec = function () {
  var mod = this

  // When module is executed, DO NOT execute it again. When module
  // is being executed, just return `module.exports` too, for avoiding
  // circularly calling
  if (mod.status >= STATUS.EXECUTING) {
    return mod.exports
  }

  mod.status = STATUS.EXECUTING

  // Create require
  var uri = mod.uri

  function require(id) {
    return Module.get(require.resolve(id)).exec()
  }

  require.resolve = function(id) {
    return Module.resolve(id, uri)
  }

  require.async = function(ids, callback) {
    Module.use(ids, callback, uri + "_async_" + cid())
    return require
  }

  // Exec factory
  var factory = mod.factory

  var exports = isFunction(factory) ?
      factory(require, mod.exports = {}, mod) :
      factory

  if (exports === undefined) {
    exports = mod.exports
  }

  // Reduce memory leak
  delete mod.factory

  mod.exports = exports
  mod.status = STATUS.EXECUTED

  // Emit `exec` event
  emit("exec", mod)

  return exports
}

// Resolve id to uri
Module.resolve = function(id, refUri) {
  // Emit `resolve` event for plugins such as text plugin
  var emitData = { id: id, refUri: refUri }
  emit("resolve", emitData)

  return emitData.uri || exports.resolve(emitData.id, refUri)
}

// Define a module
Module.define = function (id, deps, factory) {
  var argsLen = arguments.length

  // define(factory)
  if (argsLen === 1) {
    factory = id
    id = undefined
  }
  else if (argsLen === 2) {
    factory = deps

    // define(deps, factory)
    if (isArray(id)) {
      deps = id
      id = undefined
    }
    // define(id, factory)
    else {
      deps = undefined
    }
  }

  // Parse dependencies according to the module factory code
  if (!isArray(deps) && isFunction(factory)) {
    deps = parseDependencies(factory.toString())
  }

  var meta = {
    id: id,
    uri: Module.resolve(id),
    deps: deps,
    factory: factory
  }

  // Try to derive uri in IE6-9 for anonymous modules
  if (!meta.uri && doc.attachEvent) {
    var script = getCurrentScript()

    if (script) {
      meta.uri = script.src
    }

    // NOTE: If the id-deriving methods above is failed, then falls back
    // to use onload event to get the uri
  }

  // Emit `define` event, used in nocache plugin, exports node version etc
  emit("define", meta)

  meta.uri ? Module.save(meta.uri, meta) :
      // Save information for "saving" work in the script onload event
      anonymousMeta = meta
}

// Save meta data to cachedMods
Module.save = function(uri, meta) {
  var mod = Module.get(uri)

  // Do NOT override already saved modules
  if (mod.status < STATUS.SAVED) {
    mod.id = meta.id || uri
    mod.dependencies = meta.deps || []
    mod.factory = meta.factory
    mod.status = STATUS.SAVED

    emit("save", mod)
  }
}

// Get an existed module or create a new one
Module.get = function(uri, deps) {
  return cachedMods[uri] || (cachedMods[uri] = new Module(uri, deps))
}

// Use function is equal to load a anonymous module
Module.use = function (ids, callback, uri) {

  var ids = furtherDependence(ids);
  var mod = Module.get(uri, isArray(ids) ? ids : [ids])

  mod.callback = function() {
    var exports = []
    var uris = mod.resolve()

    for (var i = 0, len = uris.length; i < len; i++) {
      exports[i] = cachedMods[uris[i]].exec()
    }

    if (callback) {
      callback.apply(global, exports)
    }

    delete mod.callback
  }

  mod.load()

  function furtherDependence(ids) {
    // use config.deps to search indirect deps
    var ids = isArray(ids) ? ids : [ids]

    // get all the indirect deps according to the config.deps
    var depsHash = {}
    var retIds = []
    var i = 0
    var configDeps = data.deps

    for(i = 0; i < ids.length; i++) {
      if(!depsHash[ids[i]]) {
        depsHash[ids[i]] = true
        retIds.push(ids[i]);

        // push the direct dependency of ids[i]
        var idsDeps = configDeps[ids[i]] || []
        var j = 0

        for(; j < idsDeps.length; j++) {
          ids.push(idsDeps[j])
        }
      }
    }

    return retIds
  }
}
// Load preload modules before all other modules
Module.preload = function (callback) {
  var preloadMods = data.preload
  var len = preloadMods.length

  if (len) {
    Module.use(preloadMods, function () {
      // Remove the loaded preload modules
      preloadMods.splice(0, len)

      // Allow preload modules to add new preload modules
      Module.preload(callback)
    }, data.cwd + "_preload_" + cid())
  }
  else {
    callback()
  }
}

// Public API

exports.use = function(ids, callback) {
  Module.preload(function() {
      Module.use(ids, callback, data.cwd + "_use_" + cid())
  })
  return exports
}

Module.define.cmd = {}
global.define = exports.define = Module.define

// For Developers

exports.Module = Module
data.fetchedList = fetchedList
data.cid = cid

exports.require = function(id) {
  var mod = Module.get(Module.resolve(id))
  if (mod.status < STATUS.EXECUTING) {
    mod.onload()
    mod.exec()
  }
  return mod.exports
}

// The root path to use for id2uri parsing
data.base = loaderDir

// The loader directory
data.dir = loaderDir

// The current working directory
data.cwd = cwd

// The charset for requesting files
data.charset = "utf-8"

// set to empty array in default
data.preload = []

// set to empty object in default
data.deps = {}

// data.alias - An object containing shorthands of module id
// data.paths - An object containing path shorthands in module id
// data.vars - The {xxx} variables in module id
// data.map - An array containing rules to map module uri
// data.debug - Debug mode. The default value is false

exports.config = function(configData) {

  for (var key in configData) {
    var curr = configData[key]
    var prev = data[key]

    // Merge object config such as alias, vars
    if (prev && isObject(prev)) {
      for (var k in curr) {
        prev[k] = curr[k]
      }
    }
    else {
      // Concat array config such as map, preload
      if (isArray(prev)) {
        curr = prev.concat(curr)
      }
      // Make sure that `data.base` is an absolute path
      else if (key === "base") {
        // Make sure end with "/"
        if (curr.slice(-1) !== "/") {
          curr += "/"
        }
        curr = addBase(curr)
      }

      // Set config
      data[key] = curr
    }
  }

  emit("config", configData)
  return exports
}

var builtInMods = [
	['globalSettings', exports.data],
	['lang.forEach', forEach],
	['lang.isType', isType],
	['lang.isObject', isObject],
	['lang.isString', isString],
	['lang.isArray', isArray],
	['lang.isFunction', isFunction],
	['lang.isNumber', isNumber],
	['lang.isRegExp', isRegExp],
	['util.request', request]
];

// define modules only exist in modern browsers
global.JSON && builtInMods.push(['lang.JSON', global.JSON]);
global.jQuery && (global.jQuery.version || '').indexOf('1.7') === 0 && builtInMods.push(['lib.jQuery', global.jQuery]);

forEach(builtInMods, function(each){
    exports.define(each[0], function(require, exports, module){
        module.exports = each[1];
    });
});


/**
 * The Sea.js plugin for concatenating HTTP requests
 */
var Module = LBF.Module
var FETCHING = Module.STATUS.FETCHING

var data = LBF.data
var comboHash = data.comboHash = {}

var comboSyntax = ["c/=/", ",/"]
// by url's length
// var comboMaxLength = 1000
// by require files number
var comboMaxLength = 20
var comboExcludes
var comboSuffix


LBF.on("load", setComboHash)
LBF.on("fetch", setRequestUri)

function ignoreComboUri(uri){
	var combo = data.combo;

	switch(typeof combo) {
		/*case 'boolean':
			return false;*/
		case 'string':
			return uri.indexOf(combo) === -1 ? true : false;
		default:
			return false;
			/*var flag = true;

			for(var i in combo) {
				if(uri.indexOf(combo[i]) !== -1) {
					flag = false;
					break;
				}
			}

			return flag;*/
	}
}

function setComboHash(uris) {
    var len = uris.length
    if (len < 2 || !data.combo) {
        return
    }

    data.comboSyntax && (comboSyntax = data.comboSyntax)
    data.comboMaxLength && (comboMaxLength = data.comboMaxLength)
    data.comboSuffix && (comboSuffix = data.comboSuffix)

    comboExcludes = data.comboExcludes
    var needComboUris = []

    for (var i = 0; i < len; i++) {
        var uri = uris[i]

        if (comboHash[uri] || ignoreComboUri(uri)) {
            continue
        }

        var mod = Module.get(uri)

        // Remove fetching and fetched uris, excluded uris, combo uris
        if (mod.status < FETCHING && !isCss(uri) && !isExcluded(uri) && !isComboUri(uri)) {
            needComboUris.push(uri)
        }
    }

    if (needComboUris.length > 1) {
        uris2paths(needComboUris)
    }
}

function setRequestUri(fetchData) {
    if(!data.combo){
        return;
    }

    fetchData.requestUri = comboHash[fetchData.uri] || fetchData.uri
}


// Helpers
var COMBO_ROOT_RE = /^(\S+:\/{2,3}[^\/]+\/)/;

function uris2paths(uris) {
    var paths = [],
        root = COMBO_ROOT_RE.exec(uris[0])[1],
        rootLen = root.length;

    forEach(uris, function(uri){
        paths.push(uri.substr(rootLen));
    });

    setHash(root, paths);
}

function setHash(root, files) {
    var copy = []
    for (var i = 0, len = files.length; i < len; i++) {
        copy[i] = files[i].replace(/\?.*$/, '')
    }
    var comboPath = root + comboSyntax[0] + copy.join(comboSyntax[1])
    if(comboSuffix) {
        comboPath += comboSuffix
    }
    var exceedMax = files.length > comboMaxLength

    // http://stackoverflow.com/questions/417142/what-is-the-maximum-length-of-a-url
    if (files.length > 1 && exceedMax) {
        var parts = splitFiles(files, comboMaxLength)

        setHash(root, parts[0])
        setHash(root, parts[1])
    } else {
        if (exceedMax) {
            throw new Error("The combo url is too long: " + comboPath)
        }

        for (var i = 0, len = files.length; i < len; i++) {
            comboHash[root + files[i]] = comboPath
        }
    }
}

function splitFiles(files, filesMaxLength) {
    for (var i = 0, len = files.length; i < len; i++) {
        if (i > filesMaxLength-1) {
            return [files.splice(0, i), files]
        }
    }
}

/*
function setHash(root, files) {
    var copy = []
    for (var i = 0, len = files.length; i < len; i++) {
        copy[i] = files[i].replace(/\?.*$/, '')
    }
    var comboPath = root + comboSyntax[0] + copy.join(comboSyntax[1])
    if(comboSuffix) {
        comboPath += comboSuffix
    }
    var exceedMax = comboPath.length > comboMaxLength

    // http://stackoverflow.com/questions/417142/what-is-the-maximum-length-of-a-url
    if (files.length > 1 && exceedMax) {
        var parts = splitFiles(files,
            comboMaxLength - (root + comboSyntax[0]).length)

        setHash(root, parts[0])
        setHash(root, parts[1])
    } else {
        if (exceedMax) {
            throw new Error("The combo url is too long: " + comboPath)
        }

        for (var i = 0, len = files.length; i < len; i++) {
            comboHash[root + files[i]] = comboPath
        }
    }
}

function splitFiles(files, filesMaxLength) {
    var sep = comboSyntax[1]
    var s = files[0]

    for (var i = 1, len = files.length; i < len; i++) {
        s += sep + files[i]
        if (s.length > filesMaxLength) {
            return [files.splice(0, i), files]
        }
    }
}
*/

var CSS_EXT_RET = /\.css(?:\?.*)?$/;
function isCss(uri){
    return CSS_EXT_RET.test(uri);
}

function isExcluded(uri) {
    if (comboExcludes) {
        return comboExcludes.test ?
            comboExcludes.test(uri) :
            comboExcludes(uri)
    }
}

function isComboUri(uri) {
    var syntax = data.comboSyntax || comboSyntax
    var s1 = syntax[0]
    var s2 = syntax[1]

    return s1 && uri.indexOf(s1) > 0 || s2 && uri.indexOf(s2) > 0
}


// For test
if (data.test) {
    var test = LBF.test || (LBF.test = {})
    test.uris2paths = uris2paths
    test.paths2hash = paths2hash
}
LBF.config({
	alias: {
		globalSettings: loaderDir + "globalSettings"
	},
	vars: {
		theme: loaderDir + "ui/themes/default"
	},
	paths:{
		app: loaderDir + 'app',
		lang: loaderDir + 'lang',
		monitor: loaderDir + 'monitor',
		lib: loaderDir + 'lib',
		ui: loaderDir + 'ui',
		util: loaderDir + 'util'
	}
});

LBF.config({
    "deps": {
        "app.Collection": ["lang.isFunction", "lang.JSON", "app.REST", "util.Promise", "util.Tasks", "lib.Backbone", "lib.underscore", "app.RESTSync"],
        "app.Model": ["lang.isFunction", "lang.JSON", "app.REST", "util.Promise", "util.Tasks", "lib.Backbone", "lib.underscore", "app.RESTSync"],
        "app.REST": ["lang.extend", "lang.forEach", "lang.JSON", "lang.isFunction", "lib.jQuery", "util.Attribute", "util.Event", "app.RESTPlugins.errorLog", "app.RESTPlugins.speedReport", "app.RESTPlugins.CSRFPatch"],
        "app.RESTPlugins.CSRFPatch": ["util.Cookie"],
        "app.RESTPlugins.errorLog": ["monitor.logger", "lang.extend"],
        "app.RESTPlugins.speedReport": ["lang.extend", "monitor.SpeedReport"],
        "app.RESTSync": ["lib.underscore"],
        "app.Router": ["lib.Backbone"],
        "app.View": ["lang.extend", "lib.jQuery", "lib.Backbone", "lib.underscore", "util.template"],
        "lang.Class": ["lang.toArray", "lang.extend"],
        "lang.extend": ["lang.isPlainObject"],
        "lang.Inject": ["lang.each"],
        "lang.isPlainObject": ["lang.isObject"],
        "lib.Backbone": ["lib.underscore", "lib.jQuery", "lang.JSON", "lib.underscore"],
        "lib.Highcharts": ["lib.jQuery"],
        "lib.jQuery": ["globalSettings", "util.Cookie"],
        "monitor.SpeedReport": ["util.report", "lang.Class", "util.serialize", "util.Attribute"],
        "ui.Nodes.Button": ["lang.browser", "ui.Nodes.Node"],
        "ui.Nodes.Checkbox": ["lib.jQuery", "ui.Nodes.Node", "lang.each", "lang.extend"],
        "ui.Nodes.Gotop": ["lib.jQuery", "util.zIndexGenerator", "ui.Nodes.Node"],
        "ui.Nodes.Node": ["lang.each", "util.defaults", "lang.extend", "lang.proxy", "lang.Inject", "util.template", "util.Attribute", "lang.trim", "lang.isString", "lib.jQuery", "lang.Class"],
        "ui.Nodes.Pagination": ["lang.isNumber", "lang.extend", "ui.Nodes.Node"],
        "ui.Nodes.Popup": ["util.Style", "lib.jQuery", "lang.browser", "ui.Nodes.Node", "util.zIndexGenerator", "{theme}/lbfUI/css/Popup.css"],
        "ui.Nodes.Radio": ["lib.jQuery", "ui.Nodes.Node", "lang.each", "lang.extend"],
        "ui.Nodes.Textarea": ["lang.browser", "ui.Nodes.Node"],
        "ui.Nodes.TextInput": ["lang.forEach", "lang.browser", "lang.isArray", "ui.Nodes.Node"],
        "ui.Nodes.Tip": ["lib.jQuery", "lang.extend", "util.zIndexGenerator", "ui.Nodes.Node", "ui.widget.Dropdown.Dropdown", "{theme}/lbfUI/css/Tip.css"],
        "ui.Plugins.Cursor": ["ui.Plugins.Plugin"],
        "ui.Plugins.Drag": ["util.Style", "ui.Plugins.Plugin", "util.zIndexGenerator", "{theme}/lbfUI/css/Drag.css"],
        "ui.Plugins.DragDrop": ["lang.extend", "ui.Plugins.Drag"],
        "ui.Plugins.Overlay": ["lang.proxy", "lang.Inject", "util.Style", "util.zIndexGenerator", "lib.jQuery", "ui.Plugins.Plugin"],
        "ui.Plugins.Pin": ["lib.jQuery", "ui.Plugins.Plugin", "util.zIndexGenerator"],
        "ui.Plugins.Plugin": ["lang.each", "lang.proxy", "ui.Nodes.Node"],
        "ui.widget.CheckboxGroup.CheckboxGroup": ["lib.jQuery", "ui.Nodes.Node", "ui.Nodes.Checkbox"],
        "ui.widget.Clipboard.Clipboard": ["ui.Nodes.Node", "lang.browser"],
        "ui.widget.ComboBox.ComboBox": ["lib.jQuery", "lang.each", "lang.isArray", "lang.isFunction", "lang.isObject", "lang.isNumber", "lang.proxy", "util.template", "lang.extend", "util.zIndexGenerator", "ui.Nodes.Node", "ui.widget.Dropdown.Dropdown", "util.xssFilter"],
        "ui.widget.DatePicker.DatePicker": ["lang.proxy", "lang.forEach", "lang.isNumber", "lang.dateTool", "lang.extend", "util.contains", "ui.widget.Dropdown.Dropdown", "ui.widget.DatePicker.DatePickerTemplate", "{theme}/lbfUI/css/DatePicker.css"],
        "ui.widget.DatePicker.DatePickerRange": ["ui.widget.DatePicker.DatePicker", "lang.extend", "lang.proxy", "ui.Nodes.Popup", "lang.dateTool", "ui.widget.DatePicker.DatePickerRangeTemplate"],
        "ui.widget.Dropdown.Dropdown": ["lang.proxy", "util.template", "util.zIndexGenerator", "ui.Nodes.Popup", "{theme}/lbfUI/css/Dropdown.css"],
        "ui.widget.FileUploader.ajaxUpload.ajaxUpload": ["lang.proxy", "ui.Nodes.Node", "ui.Nodes.Button", "lang.isFunction", "lang.browser", "util.Cookie"],
        "ui.widget.FileUploader.FileUploader": ["lib.jQuery", "ui.Nodes.Node", "{theme}/lbfUI/css/FileUploader.css"],
        "ui.widget.FileUploader.iframeUpload.iframeUpload": ["lang.proxy", "lang.browser", "ui.Nodes.Node", "ui.Nodes.Button", "lang.isFunction", "util.Cookie", "lang.extend"],
        "ui.widget.FileUploader.swfUpload.init": ["lib.jQuery", "lang.proxy", "lang.extend", "ui.Nodes.Node", "ui.Nodes.Button", "lang.isFunction", "util.Cookie", "ui.widget.FileUploader.swfUpload.swfUploadQueue"],
        "ui.widget.FileUploader.swfUpload.swfUploadQueue": ["ui.widget.FileUploader.swfUpload.swfUpload"],
        "ui.widget.ImageCrop.ImageCrop": ["ui.Nodes.Node", "lib.jQuery", "lang.isFunction", "ui.widget.Panel.Panel", "lang.extend", "ui.widget.FileUploader.FileUploader", "util.imageLoader", "{theme}/lbfUI/css/ImageCrop.css", "ui.widget.ImageCrop.mouseWheel"],
        "ui.widget.ImageViewer.ImageViewer": ["lang.proxy", "lang.browser", "ui.Nodes.Tip"],
        "ui.widget.JScrollPane.JScrollPane": ["lib.jQuery", "util.mouseWheel", "{theme}/lbfUI/css/JScrollPane.css"],
        "ui.widget.LightTip.LightTip": ["lib.jQuery", "lang.extend", "ui.Nodes.Node", "ui.Nodes.Popup", "ui.Plugins.Overlay", "util.zIndexGenerator", "{theme}/lbfUI/css/LightTip.css"],
        "ui.widget.Menu.Menu": ["lang.proxy", "lang.extend", "lang.forEach", "lang.isArray", "util.template", "util.zIndexGenerator", "ui.widget.Dropdown.Dropdown", "{theme}/lbfUI/css/Menu.css"],
        "ui.widget.NumberSpinner.NumberSpinner": ["lang.isNumber", "lang.extend", "ui.Nodes.Node", "{theme}/lbfUI/css/NumberSpinner.css"],
        "ui.widget.Panel.Panel": ["lib.jQuery", "lang.forEach", "lang.proxy", "lang.extend", "lang.Inject", "util.zIndexGenerator", "util.Shortcuts", "ui.Nodes.Node", "ui.Nodes.Popup", "ui.Nodes.Button", "ui.Plugins.Drag", "ui.Plugins.Overlay", "{theme}/lbfUI/css/Panel.css"],
        "ui.widget.RegionSelector.RegionSelector": ["ui.Nodes.Node", "ui.widget.ComboBox.ComboBox", "util.regionData", "lang.each", "lang.isArray"],
        "ui.widget.Scrollspy.Scrollspy": ["lib.jQuery", "ui.Nodes.Node", "lang.extend", "{theme}/lbfUI/css/Scrollspy.css"],
        "ui.widget.Slides.Slides": ["lib.jQuery", "ui.Nodes.Node", "{theme}/lbfUI/css/Slides.css"],
        "ui.widget.State.State": ["ui.Nodes.Node", "ui.Nodes.Popup", "lang.proxy", "lang.extend", "lib.jQuery"],
        "ui.widget.Switchable.Switchable": ["lib.jQuery", "ui.Nodes.Node"],
        "ui.widget.TimePicker.TimePicker": ["lib.jQuery", "lang.extend", "ui.widget.Dropdown.Dropdown", "ui.widget.TimePicker.TimePickerTemplate", "{theme}/lbfUI/css/TimePicker.css"],
        "ui.widget.ZTree.ZTree": ["lib.jQuery", "{theme}/lbfUI/css/zTreeStyle.css"],
        "util.Attribute": ["lang.extend"],
        "util.Callbacks": ["lang.Class", "lang.forEach", "lang.extend", "lang.isFunction", "lang.isString", "lang.inArray"],
        "util.Event": ["lang.toArray", "util.Callbacks"],
        "util.eventProxy": ["lang.extend", "lib.Backbone"],
        "util.imageLoader": ["lang.browser"],
        "util.jsonp": ["util.request", "util.serialize"],
        "util.localStorage": ["util.Cookie", "lang.trim"],
        "util.Promise": ["lang.Class", "lang.forEach", "lang.extend", "lang.proxy", "lang.toArray", "lang.isFunction", "util.Callbacks"],
        "util.ptLoginHelper": ["util.RSA", "util.TEA", "util.md5"],
        "util.PubSub": ["lang.extend", "util.Event"],
        "util.Range": ["lang.Class", "lang.isString", "util.contains"],
        "util.requestAnimationFrame": ["lang.proxy"],
        "util.Selection": ["lang.Class", "util.Range"],
        "util.sessionStorage": ["util.localStorage", "util.Cookie", "util.domain"],
        "util.Style": ["lib.jQuery"],
        "util.Tasks": ["lang.proxy"],
        "util.Validform": ["{theme}/lbfUI/css/Validform.css", "ui.Nodes.Node", "lib.jQuery", "lang.extend", "lang.isFunction", "lang.isRegExp"]
    }
});
})(this);
