(function(e){function t(t){for(var r,o,c=t[0],i=t[1],s=t[2],l=0,d=[];l<c.length;l++)o=c[l],Object.prototype.hasOwnProperty.call(a,o)&&a[o]&&d.push(a[o][0]),a[o]=0;for(r in i)Object.prototype.hasOwnProperty.call(i,r)&&(e[r]=i[r]);f&&f(t);while(d.length)d.shift()();return u.push.apply(u,s||[]),n()}function n(){for(var e,t=0;t<u.length;t++){for(var n=u[t],r=!0,o=1;o<n.length;o++){var c=n[o];0!==a[c]&&(r=!1)}r&&(u.splice(t--,1),e=i(i.s=n[0]))}return e}var r={},o={index:0},a={index:0},u=[];function c(e){return i.p+"js/"+({}[e]||e)+"."+{"chunk-11b6010b":"2993c634","chunk-6e2d603b":"8b921e4e","chunk-761c3f2a":"abb3f053","chunk-a8dd29e6":"e0d48b7a","chunk-be9f3ea0":"c7acc562"}[e]+".js"}function i(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,i),n.l=!0,n.exports}i.e=function(e){var t=[],n={"chunk-11b6010b":1,"chunk-6e2d603b":1,"chunk-761c3f2a":1,"chunk-a8dd29e6":1,"chunk-be9f3ea0":1};o[e]?t.push(o[e]):0!==o[e]&&n[e]&&t.push(o[e]=new Promise((function(t,n){for(var r="css/"+({}[e]||e)+"."+{"chunk-11b6010b":"01f31294","chunk-6e2d603b":"1ae35701","chunk-761c3f2a":"8bb2d186","chunk-a8dd29e6":"4c0f6287","chunk-be9f3ea0":"81f2cc17"}[e]+".css",a=i.p+r,u=document.getElementsByTagName("link"),c=0;c<u.length;c++){var s=u[c],l=s.getAttribute("data-href")||s.getAttribute("href");if("stylesheet"===s.rel&&(l===r||l===a))return t()}var d=document.getElementsByTagName("style");for(c=0;c<d.length;c++){s=d[c],l=s.getAttribute("data-href");if(l===r||l===a)return t()}var f=document.createElement("link");f.rel="stylesheet",f.type="text/css",f.onload=t,f.onerror=function(t){var r=t&&t.target&&t.target.src||a,u=new Error("Loading CSS chunk "+e+" failed.\n("+r+")");u.code="CSS_CHUNK_LOAD_FAILED",u.request=r,delete o[e],f.parentNode.removeChild(f),n(u)},f.href=a;var p=document.getElementsByTagName("head")[0];p.appendChild(f)})).then((function(){o[e]=0})));var r=a[e];if(0!==r)if(r)t.push(r[2]);else{var u=new Promise((function(t,n){r=a[e]=[t,n]}));t.push(r[2]=u);var s,l=document.createElement("script");l.charset="utf-8",l.timeout=120,i.nc&&l.setAttribute("nonce",i.nc),l.src=c(e);var d=new Error;s=function(t){l.onerror=l.onload=null,clearTimeout(f);var n=a[e];if(0!==n){if(n){var r=t&&("load"===t.type?"missing":t.type),o=t&&t.target&&t.target.src;d.message="Loading chunk "+e+" failed.\n("+r+": "+o+")",d.name="ChunkLoadError",d.type=r,d.request=o,n[1](d)}a[e]=void 0}};var f=setTimeout((function(){s({type:"timeout",target:l})}),12e4);l.onerror=l.onload=s,document.head.appendChild(l)}return Promise.all(t)},i.m=e,i.c=r,i.d=function(e,t,n){i.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},i.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},i.t=function(e,t){if(1&t&&(e=i(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(i.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)i.d(n,r,function(t){return e[t]}.bind(null,r));return n},i.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return i.d(t,"a",t),t},i.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},i.p="/uploadersrealm/",i.oe=function(e){throw console.error(e),e};var s=window["webpackJsonp"]=window["webpackJsonp"]||[],l=s.push.bind(s);s.push=t,s=s.slice();for(var d=0;d<s.length;d++)t(s[d]);var f=l;u.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";var r=n("85ec"),o=n.n(r);o.a},"11e1":function(e,t,n){},"2e2c":function(e,t,n){},"56c5":function(e,t,n){"use strict";var r=n("2e2c"),o=n.n(r);o.a},"56d7":function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d");var r=n("2b0e"),o=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("Header"),n("keep-alive",[n("router-view")],1)],1)},a=[],u=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"header"}},[n("header",[n("img",{attrs:{src:"bee.png",alt:"bee"}}),n("h1",[n("router-link",{attrs:{to:"/"}},[e._v("HONEYCOMB STORAGE")])],1),n("img",{attrs:{src:"honeycomb.png",alt:"honeycomb"}})]),n("nav",[n("ol",[n("li",[n("router-link",{attrs:{to:"/consumers"}},[e._v("Consumers")])],1),n("li",[n("router-link",{attrs:{to:"/files"}},[e._v("Files")])],1),n("li",[n("router-link",{attrs:{to:"/addconsumer"}},[e._v("Add Consumer")])],1),n("li",[n("router-link",{attrs:{to:"/addfile"}},[e._v("Add File")])],1),n("li",[n("router-link",{attrs:{to:"/privatearea"}},[n("img",{attrs:{src:e.profile_picture,alt:"profile_picture"},on:{error:function(t){return e.changeURL()}}})])],1)])])])},c=[],i={name:"Header",methods:{changeURL:function(){this.profile_picture="default_profile_picture.png"}},data:function(){return{profile_picture:"".concat("/api/","uploaderarea/logo/")}}},s=i,l=(n("c558"),n("2877")),d=Object(l["a"])(s,u,c,!1,null,"50962a54",null),f=d.exports,p={name:"App",components:{Header:f}},m=p,h=(n("034f"),Object(l["a"])(m,o,a,!1,null,null,null)),v=h.exports,b=(n("d3b7"),n("8c4f")),_=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("main",[n("h1",[e._v("Welcome, "+e._s(e.uploaderInfo.username)+"!")]),n("p",[e._v(" You are an "),n("em",[e._v(e._s(e.uploaderInfo.role))]),e._v(" that have uploaded "),n("em",[e._v(e._s(e.uploaderInfo.total_files))]),e._v(" files and have a total of "),n("em",[e._v(e._s(e.uploaderInfo.total_consumers))]),e._v(" associated consumers. In HoneycombStorage you can add consumers that will receive the files you will upload. Enjoy HoneycombStorage platform now! ")]),n("section",[n("table",[0!==e.consumers.length?n("tbody",e._l(e.consumers,(function(t){return n("tr",{key:t.username},[n("td",{on:{click:function(n){return e.changeActiveConsumer(t.username)}}},[e._v(" "+e._s(t.username)+" ")]),n("td",[e._v("("+e._s(t.files.length)+")")])])})),0):n("tbody",{staticStyle:{display:"flex","flex-direction":"row","align-items":"center"}},[n("em",{staticStyle:{"text-align":"center","flex-basis":"100%"}},[e._v(" You have not affiliate consumers ")])])]),void 0!=e.activeConsumer?n("ConsumerFilesTable",{attrs:{files:e.activeConsumer.files}}):e._e()],1)])},g=[],y=(n("4de4"),n("4160"),n("159b"),n("bc3a")),k=n.n(y),w=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("table",[e._m(0),e.files.length>0?n("tbody",e._l(e.files,(function(t){return n("tr",{key:t.id},[n("td",[e._v(e._s(t.name))]),""!==t.seen?n("td",[e._v(e._s(t.seen))]):n("td",[n("span",[e._v("file not seen yet")])]),""!==t.seen?n("td",[e._v(" "+e._s(t.ipAddress)+" ")]):n("td",[e._v("-")])])})),0):n("tbody",{staticStyle:{display:"flex","flex-direction":"row","align-items":"center"}},[n("em",{staticStyle:{"text-align":"center","flex-basis":"100%"}},[e._v(" you have not uploaded any file to this consumer yet")])])])},x=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("thead",[n("tr",[n("th",[e._v("Nome File")]),n("th",[e._v("Seen Date")]),n("th",[e._v("IP Address")])])])}],C={name:"ConsumerFilesTable",props:["files"]},A=C,O=(n("80d5"),Object(l["a"])(A,w,x,!1,null,"60f5c683",null)),E=O.exports,S={name:"Home",components:{ConsumerFilesTable:E},methods:{changeActiveConsumer:function(e){this.activeConsumer=this.consumers.filter((function(t){return t.username===e}))[0],document.querySelectorAll("table:first-of-type tbody tr td:first-child").forEach((function(t){t.innerText===e?t.style.color="rgb(255, 255, 255)":t.style.color="rgb(214, 139, 0)"}))}},data:function(){return{uploaderInfo:{},consumers:[],activeConsumer:void 0}},created:function(){var e=this;k.a.get("".concat("/api/","uploaderarea/generalinfo")).then((function(t){return e.uploaderInfo=t.data})).catch((function(e){return console.log(e)})),k.a.get("".concat("/api/","uploaderarea/consumersextended")).then((function(t){e.consumers=t.data,e.activeConsumer=e.consumers[0]})).catch((function(e){return console.log(e)}))}},j=S,T=(n("56c5"),Object(l["a"])(j,_,g,!1,null,"90a0b120",null)),P=T.exports;r["a"].use(b["a"]);var F=[{path:"/",name:"Home",component:P},{path:"/AddConsumer",name:"AddConsumer",component:function(){return n.e("chunk-11b6010b").then(n.bind(null,"fbf0"))}},{path:"/addfile",name:"AddFile",component:function(){return n.e("chunk-be9f3ea0").then(n.bind(null,"817b"))}},{path:"/privatearea",name:"PrivateArea",component:function(){return n.e("chunk-a8dd29e6").then(n.bind(null,"d45d"))}},{path:"/consumers",name:"Consumers",component:function(){return n.e("chunk-761c3f2a").then(n.bind(null,"d02b"))}},{path:"/files",name:"ManageFiles",component:function(){return n.e("chunk-6e2d603b").then(n.bind(null,"054f"))}}],H=new b["a"]({routes:F}),I=H;r["a"].config.productionTip=!1,new r["a"]({router:I,render:function(e){return e(v)}}).$mount("#app")},7546:function(e,t,n){},"80d5":function(e,t,n){"use strict";var r=n("7546"),o=n.n(r);o.a},"85ec":function(e,t,n){},c558:function(e,t,n){"use strict";var r=n("11e1"),o=n.n(r);o.a}});
//# sourceMappingURL=index.cce77448.js.map