(function(e){function t(t){for(var r,a,c=t[0],i=t[1],s=t[2],l=0,d=[];l<c.length;l++)a=c[l],Object.prototype.hasOwnProperty.call(o,a)&&o[a]&&d.push(o[a][0]),o[a]=0;for(r in i)Object.prototype.hasOwnProperty.call(i,r)&&(e[r]=i[r]);f&&f(t);while(d.length)d.shift()();return u.push.apply(u,s||[]),n()}function n(){for(var e,t=0;t<u.length;t++){for(var n=u[t],r=!0,a=1;a<n.length;a++){var c=n[a];0!==o[c]&&(r=!1)}r&&(u.splice(t--,1),e=i(i.s=n[0]))}return e}var r={},a={index:0},o={index:0},u=[];function c(e){return i.p+"js/"+({}[e]||e)+"."+{"chunk-1aab2a91":"6f8dae89","chunk-46eae774":"e9767a20","chunk-5d6a5c0c":"fb8a1b54","chunk-961284b6":"6ad84134","chunk-a4775638":"8800ea6c"}[e]+".js"}function i(t){if(r[t])return r[t].exports;var n=r[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,i),n.l=!0,n.exports}i.e=function(e){var t=[],n={"chunk-1aab2a91":1,"chunk-46eae774":1,"chunk-5d6a5c0c":1,"chunk-961284b6":1,"chunk-a4775638":1};a[e]?t.push(a[e]):0!==a[e]&&n[e]&&t.push(a[e]=new Promise((function(t,n){for(var r="css/"+({}[e]||e)+"."+{"chunk-1aab2a91":"a4370ea1","chunk-46eae774":"7ab732bd","chunk-5d6a5c0c":"aa6c7e15","chunk-961284b6":"f23b2dfb","chunk-a4775638":"fbe227a2"}[e]+".css",o=i.p+r,u=document.getElementsByTagName("link"),c=0;c<u.length;c++){var s=u[c],l=s.getAttribute("data-href")||s.getAttribute("href");if("stylesheet"===s.rel&&(l===r||l===o))return t()}var d=document.getElementsByTagName("style");for(c=0;c<d.length;c++){s=d[c],l=s.getAttribute("data-href");if(l===r||l===o)return t()}var f=document.createElement("link");f.rel="stylesheet",f.type="text/css",f.onload=t,f.onerror=function(t){var r=t&&t.target&&t.target.src||o,u=new Error("Loading CSS chunk "+e+" failed.\n("+r+")");u.code="CSS_CHUNK_LOAD_FAILED",u.request=r,delete a[e],f.parentNode.removeChild(f),n(u)},f.href=o;var p=document.getElementsByTagName("head")[0];p.appendChild(f)})).then((function(){a[e]=0})));var r=o[e];if(0!==r)if(r)t.push(r[2]);else{var u=new Promise((function(t,n){r=o[e]=[t,n]}));t.push(r[2]=u);var s,l=document.createElement("script");l.charset="utf-8",l.timeout=120,i.nc&&l.setAttribute("nonce",i.nc),l.src=c(e);var d=new Error;s=function(t){l.onerror=l.onload=null,clearTimeout(f);var n=o[e];if(0!==n){if(n){var r=t&&("load"===t.type?"missing":t.type),a=t&&t.target&&t.target.src;d.message="Loading chunk "+e+" failed.\n("+r+": "+a+")",d.name="ChunkLoadError",d.type=r,d.request=a,n[1](d)}o[e]=void 0}};var f=setTimeout((function(){s({type:"timeout",target:l})}),12e4);l.onerror=l.onload=s,document.head.appendChild(l)}return Promise.all(t)},i.m=e,i.c=r,i.d=function(e,t,n){i.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},i.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},i.t=function(e,t){if(1&t&&(e=i(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(i.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var r in e)i.d(n,r,function(t){return e[t]}.bind(null,r));return n},i.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return i.d(t,"a",t),t},i.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},i.p="/uploadersrealm/",i.oe=function(e){throw console.error(e),e};var s=window["webpackJsonp"]=window["webpackJsonp"]||[],l=s.push.bind(s);s.push=t,s=s.slice();for(var d=0;d<s.length;d++)t(s[d]);var f=l;u.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";var r=n("85ec"),a=n.n(r);a.a},"47ff":function(e,t,n){},"48a2":function(e,t,n){},"56d7":function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d");var r=n("2b0e"),a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("Header"),n("keep-alive",[n("router-view")],1)],1)},o=[],u=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"header"}},[n("header",[n("img",{attrs:{src:"bee.png",alt:"bee"}}),n("h1",[n("router-link",{attrs:{to:"/"}},[e._v("HONEYCOMB STORAGE")])],1),n("img",{attrs:{src:"honeycomb.png",alt:"honeycomb"}})]),n("nav",[n("ol",[n("li",[n("router-link",{attrs:{to:"/consumers"}},[e._v("Consumers")])],1),n("li",[n("router-link",{attrs:{to:"/files"}},[e._v("Files")])],1),n("li",[n("router-link",{attrs:{to:"/addconsumer"}},[e._v("Add Consumer")])],1),n("li",[n("router-link",{attrs:{to:"/addfile"}},[e._v("Add File")])],1),n("li",[n("router-link",{attrs:{to:"/privatearea"}},[n("img",{attrs:{src:e.profile_picture,alt:"profile_picture"},on:{error:function(t){return e.changeURL()}}})])],1)])])])},c=[],i={name:"Header",methods:{changeURL:function(){this.profile_picture="default_profile_picture.png"}},data:function(){return{profile_picture:"".concat("/api/","uploaderarea/logo/")}}},s=i,l=(n("c82d"),n("2877")),d=Object(l["a"])(s,u,c,!1,null,"7c4804dd",null),f=d.exports,p={name:"App",components:{Header:f}},m=p,h=(n("034f"),Object(l["a"])(m,a,o,!1,null,null,null)),v=h.exports,b=(n("d3b7"),n("8c4f")),_=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("main",[n("h1",[e._v("Welcome, "+e._s(e.uploaderInfo.username)+"!")]),n("p",[e._v(" You are an "),n("em",[e._v(e._s(e.uploaderInfo.role))]),e._v(" that have uploaded "),n("em",[e._v(e._s(e.uploaderInfo.total_files))]),e._v(" files and have a total of "),n("em",[e._v(e._s(e.uploaderInfo.total_consumers))]),e._v(" associated consumers. In HoneycombStorage you can add consumers that will receive the files you will upload. Enjoy HoneycombStorage platform now! ")]),n("section",[n("table",[0!==e.consumers.length?n("tbody",e._l(e.consumers,(function(t){return n("tr",{key:t.username},[n("td",{on:{click:function(n){return e.changeActiveConsumer(t.username)}}},[e._v(" "+e._s(t.username)+" ")]),n("td",[e._v("("+e._s(t.files.length)+")")])])})),0):n("tbody",{staticStyle:{display:"flex","flex-direction":"row","align-items":"center"}},[n("em",{staticStyle:{"text-align":"center","flex-basis":"100%"}},[e._v(" You have not affiliate consumers ")])])]),void 0!=e.activeConsumer?n("ConsumerFilesTable",{attrs:{files:e.activeConsumer.files}}):e._e()],1)])},g=[],y=(n("4de4"),n("4160"),n("159b"),n("bc3a")),k=n.n(y),w=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("table",[e._m(0),e.files.length>0?n("tbody",e._l(e.files,(function(t){return n("tr",{key:t.id},[n("td",[e._v(e._s(t.name))]),""!==t.seen?n("td",[e._v(e._s(t.seen))]):n("td",[n("span",[e._v("file not seen yet")])]),""!==t.seen?n("td",[e._v(" "+e._s(t.ipAddress)+" ")]):n("td",[e._v("-")])])})),0):n("tbody",{staticStyle:{display:"flex","flex-direction":"row","align-items":"center"}},[n("em",{staticStyle:{"text-align":"center","flex-basis":"100%"}},[e._v(" you have not uploaded any file to this consumer yet")])])])},x=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("thead",[n("tr",[n("th",[e._v("Nome File")]),n("th",[e._v("Seen Date")]),n("th",[e._v("IP Address")])])])}],C={name:"ConsumerFilesTable",props:["files"]},A=C,O=(n("aa83"),Object(l["a"])(A,w,x,!1,null,"045f4a60",null)),E=O.exports,S={name:"Home",components:{ConsumerFilesTable:E},methods:{changeActiveConsumer:function(e){this.activeConsumer=this.consumers.filter((function(t){return t.username===e}))[0],document.querySelectorAll("table:first-of-type tbody tr td:first-child").forEach((function(t){t.innerText===e?t.style.color="rgb(255, 255, 255)":t.style.color="rgb(214, 139, 0)"}))}},data:function(){return{uploaderInfo:{},consumers:[],activeConsumer:void 0}},created:function(){var e=this;k.a.get("".concat("/api/","uploaderarea/generalinfo")).then((function(t){return e.uploaderInfo=t.data})).catch((function(e){return console.log(e)})),k.a.get("".concat("/api/","uploaderarea/consumersextended")).then((function(t){e.consumers=t.data,e.activeConsumer=e.consumers[0]})).catch((function(e){return console.log(e)}))}},j=S,T=(n("ee03"),Object(l["a"])(j,_,g,!1,null,"df16d612",null)),P=T.exports;r["a"].use(b["a"]);var F=[{path:"/",name:"Home",component:P},{path:"/AddConsumer",name:"AddConsumer",component:function(){return n.e("chunk-1aab2a91").then(n.bind(null,"fbf0"))}},{path:"/addfile",name:"AddFile",component:function(){return n.e("chunk-961284b6").then(n.bind(null,"817b"))}},{path:"/privatearea",name:"PrivateArea",component:function(){return n.e("chunk-46eae774").then(n.bind(null,"d45d"))}},{path:"/consumers",name:"Consumers",component:function(){return n.e("chunk-5d6a5c0c").then(n.bind(null,"d02b"))}},{path:"/files",name:"ManageFiles",component:function(){return n.e("chunk-a4775638").then(n.bind(null,"054f"))}}],H=new b["a"]({routes:F}),I=H;r["a"].config.productionTip=!1,new r["a"]({router:I,render:function(e){return e(v)}}).$mount("#app")},"5ce0":function(e,t,n){},"85ec":function(e,t,n){},aa83:function(e,t,n){"use strict";var r=n("48a2"),a=n.n(r);a.a},c82d:function(e,t,n){"use strict";var r=n("47ff"),a=n.n(r);a.a},ee03:function(e,t,n){"use strict";var r=n("5ce0"),a=n.n(r);a.a}});
//# sourceMappingURL=index.36b573f6.js.map