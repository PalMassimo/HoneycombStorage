(function(e){function t(t){for(var a,s,i=t[0],l=t[1],u=t[2],d=0,f=[];d<i.length;d++)s=i[d],Object.prototype.hasOwnProperty.call(r,s)&&r[s]&&f.push(r[s][0]),r[s]=0;for(a in l)Object.prototype.hasOwnProperty.call(l,a)&&(e[a]=l[a]);c&&c(t);while(f.length)f.shift()();return o.push.apply(o,u||[]),n()}function n(){for(var e,t=0;t<o.length;t++){for(var n=o[t],a=!0,i=1;i<n.length;i++){var l=n[i];0!==r[l]&&(a=!1)}a&&(o.splice(t--,1),e=s(s.s=n[0]))}return e}var a={},r={index:0},o=[];function s(t){if(a[t])return a[t].exports;var n=a[t]={i:t,l:!1,exports:{}};return e[t].call(n.exports,n,n.exports,s),n.l=!0,n.exports}s.m=e,s.c=a,s.d=function(e,t,n){s.o(e,t)||Object.defineProperty(e,t,{enumerable:!0,get:n})},s.r=function(e){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(e,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(e,"__esModule",{value:!0})},s.t=function(e,t){if(1&t&&(e=s(e)),8&t)return e;if(4&t&&"object"===typeof e&&e&&e.__esModule)return e;var n=Object.create(null);if(s.r(n),Object.defineProperty(n,"default",{enumerable:!0,value:e}),2&t&&"string"!=typeof e)for(var a in e)s.d(n,a,function(t){return e[t]}.bind(null,a));return n},s.n=function(e){var t=e&&e.__esModule?function(){return e["default"]}:function(){return e};return s.d(t,"a",t),t},s.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)},s.p="/HoneycombStorage/consumersrealm/";var i=window["webpackJsonp"]=window["webpackJsonp"]||[],l=i.push.bind(i);i.push=t,i=i.slice();for(var u=0;u<i.length;u++)t(i[u]);var c=l;o.push([0,"chunk-vendors"]),n()})({0:function(e,t,n){e.exports=n("56d7")},"034f":function(e,t,n){"use strict";var a=n("85ec"),r=n.n(a);r.a},"0828":function(e,t,n){"use strict";var a=n("3771"),r=n.n(a);r.a},"0d54":function(e,t,n){"use strict";var a=n("a185"),r=n.n(a);r.a},"211b":function(e,t,n){"use strict";var a=n("5639"),r=n.n(a);r.a},2889:function(e,t,n){"use strict";var a=n("dcf2"),r=n.n(a);r.a},3771:function(e,t,n){},"519c":function(e,t,n){},5639:function(e,t,n){},"56d7":function(e,t,n){"use strict";n.r(t);n("e260"),n("e6cf"),n("cca6"),n("a79d");var a=n("2b0e"),r=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("div",{attrs:{id:"app"}},[n("Header"),n("router-view",{attrs:{uploaders:e.uploaders}})],1)},o=[],s=(n("4160"),n("159b"),n("bc3a")),i=n.n(s),l=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("header",[n("h1",[n("img",{attrs:{src:"bee.png",alt:"bee"}}),n("router-link",{attrs:{to:"/"}},[e._v("HONEYCOMB STORAGE")]),n("img",{attrs:{src:"honeycomb.png",alt:"honeycomb"}})],1),n("router-link",{attrs:{to:"/PrivateArea"}},[e._v("Private Area")])],1)},u=[],c={name:"Header"},d=c,f=(n("2889"),n("2877")),p=Object(f["a"])(d,l,u,!1,null,"69015921",null),m=p.exports,v={name:"App",components:{Header:m},data:function(){return{uploaders:{}}},created:function(){var e=this;i.a.get("".concat("/HoneycombStorage/api/","consumerarea/consumernews")).then((function(t){e.uploaders=t.data;var n=[];e.uploaders.forEach((function(e){var t={unseenFiles:0,username:e.username};e.files.forEach((function(e){""===e.seen&&t.unseenFiles++})),n.push(t)}));var a={username:"",change:!1};n.forEach((function(e){0!==e.unseenFiles&&!1===a.change&&"none"!==a.username?(a.username=e.username,a.change=!0):0!==e.unseenFiles&&!0===a.change&&(a.change=!1,a.username="none")})),a.change&&e.$router.push({name:"Uploader",params:{username:a.username}})})).catch((function(e){return console.log(e)}))}},h=v,g=(n("034f"),Object(f["a"])(h,r,o,!1,null,null,null)),_=g.exports,y=n("8c4f"),b=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("main",[n("p",[e._v("There are "+e._s(e.totUploaders)+" uploaders that send you files")]),n("section",e._l(e.uploaders,(function(t,a){return n("router-link",{key:a,staticClass:"router-link",attrs:{to:{path:"/Uploader/"+t.username}},on:{click:function(t){return e.setCurrentUploader(a)}}},[n("UploaderCard",{attrs:{uploader:t}})],1)})),1)])},w=[],U=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("article",[n("img",{attrs:{src:this.logo,alt:"logo_uploader"}}),n("h1",[e._v("Uploader "+e._s(e.uploader.username))]),0!==e.unreadFiles?n("p",[e._v("Has sent "+e._s(e.unreadFiles)+" new files to you!")]):n("p",[e._v("has no new files for you...")])])},F=[],O={props:["uploader"],name:"UploaderCard",computed:{unreadFiles:function(){var e=0;return void 0!==this.uploader.files?(this.uploader.files.forEach((function(t){""===t.seen&&e++})),e):0},logo:function(){return""===this.uploader.logo?"/default_profile_picture.png":"data:image/jpeg;base64, ".concat(this.uploader.logo)}}},S=O,k=(n("a137"),Object(f["a"])(S,U,F,!1,null,"6c25c96c",null)),x=k.exports,E={name:"Home",components:{UploaderCard:x},props:["uploaders"],computed:{totUploaders:function(){return this.uploaders.length}}},P=E,j=(n("0d54"),Object(f["a"])(P,b,w,!1,null,"c4ce0a42",null)),C=j.exports,$=function(){var e=this,t=e.$createElement,n=e._self._c||t;return null!==e.currentUploader?n("main",[n("UploaderItem",{staticClass:"uploaderItem",attrs:{uploader:e.currentUploader}}),void 0!==e.currentFiles?n("FileTable",{staticClass:"FileTable",attrs:{files:e.currentFiles}}):e._e()],1):e._e()},H=[],T=(n("99af"),n("4de4"),n("d81d"),function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("article",[n("img",{attrs:{src:this.logo,alt:"logo_uploader"}}),n("em",[e._v("Uploader "+e._s(e.uploader.username))])])}),A=[],N={props:["uploader"],name:"UploaderItem",computed:{unreadFiles:function(){var e=0;return void 0!==this.uploader.files?(this.uploader.files.forEach((function(t){""===t.seen&&e++})),e):0},logo:function(){return""===this.uploader.logo?"/default_profile_picture.png":"data:image/jpeg;base64, ".concat(this.uploader.logo)}}},D=N,M=(n("9cac"),Object(f["a"])(D,T,A,!1,null,"c39a0038",null)),R=M.exports,B=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("section",[n("div",[n("input",{directives:[{name:"model",rawName:"v-model",value:e.hashtag,expression:"hashtag"}],attrs:{type:"text",placeholder:"filter by hashtag..."},domProps:{value:e.hashtag},on:{input:function(t){t.target.composing||(e.hashtag=t.target.value)}}})]),n("table",[e._m(0),n("tbody",e._l(e.filesFilter,(function(t,a){return n("tr",{key:a},[n("td",[e._v(e._s(t.name))]),n("td",[e._v(e._s(t.hashtags.join(", ")))]),n("td",[e._v(e._s(t.uploaded))]),""!==t.seen?n("td",[e._v(e._s(t.seen))]):n("td",[e._v("unseen file")]),n("td",[n("a",{attrs:{href:t.link}},[n("img",{attrs:{src:"download_icon.png",alt:"download"}})])])])})),0)])])},I=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("thead",[n("tr",[n("th",[e._v("Name")]),n("th",[e._v("Hashtags")]),n("th",[e._v("Uploaded")]),n("th",[e._v("Seen")]),n("th",[e._v("Download")])])])}],W=(n("caad"),n("2532"),{props:["files"],data:function(){return{hashtag:""}},computed:{filesFilter:function(){var e=this;return""!==this.hashtag?this.files.filter((function(t){for(var n=0;n<t.hashtags.length;n++)if(t.hashtags[n].toLowerCase().includes(e.hashtag.toLowerCase()))return!0;return!1})):this.files}}}),G=W,J=(n("91e2"),Object(f["a"])(G,B,I,!1,null,"9a65d2fc",null)),L=J.exports,Y={name:"Uploader",components:{UploaderItem:R,FileTable:L},props:["uploaders"],methods:{compareElementsBySeen:function(e,t){return""!==e.seen&&""===t.seen?-1:""===e.seen&&""!==t.seen?1:0},compareElementsByUploadDate:function(e,t){var n=new Date(e.uploaded),a=new Date(t.uploaded);return a-n}},computed:{currentFiles:function(){var e=this;if(this.uploaders.length>0){var t=this.uploaders.filter((function(t){return t.username===e.$route.params.username})).map((function(e){return e["files"]}))[0],n=t.filter((function(e){return""===e.seen})),a=t.filter((function(e){return""!==e.seen}));return n.sort(this.compareElementsByUploadDate),a.sort(this.compareElementsByUploadDate),n.concat(a)}return null},currentUploader:function(){var e=this;return this.uploaders.length>0?this.uploaders.filter((function(t){return t.username===e.$route.params.username}))[0]:null}}},q=Y,z=(n("211b"),Object(f["a"])(q,$,H,!1,null,"01e2a365",null)),K=z.exports,Q=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("main",[n("h1",[e._v("Private Area")]),n("p",[e._v(" Dear "+e._s(e.info.username)+", these are the information about you. Here you can change your name, surname, email and password. To confirm your changes simply click on 'confirm changes'. "),0===e.info.totUnseenFiles?n("em",[e._v("All files have been read.")]):n("em",[e._v("There are files you don't have downloaded yet")])]),e.info!=={}?n("table",[e._m(0),n("tr",[n("th",[e._v("Username")]),n("td",[e._v(e._s(e.info.username))])]),n("tr",[n("th",[e._v("Name, Surname")]),n("td",[n("input",{directives:[{name:"model",rawName:"v-model",value:e.info.nameSurname.value,expression:"info.nameSurname.value"}],class:[e.info.nameSurname.active?"active":"inactive"],attrs:{type:"text",readonly:""},domProps:{value:e.info.nameSurname.value},on:{click:function(t){return e.setWritable(e.info.nameSurname)},keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.setReadOnly(e.info.nameSurname)},input:function(t){t.target.composing||e.$set(e.info.nameSurname,"value",t.target.value)}}})])]),n("tr",[n("th",[e._v("Email")]),n("td",[n("input",{directives:[{name:"model",rawName:"v-model",value:e.info.email.value,expression:"info.email.value"}],class:[e.info.email.active?"active":"inactive"],attrs:{type:"text",readonly:""},domProps:{value:e.info.email.value},on:{click:function(t){return e.setWritable(e.info.email)},keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.setReadOnly(e.info.email)},input:function(t){t.target.composing||e.$set(e.info.email,"value",t.target.value)}}})])]),n("tr",[n("th",[e._v("Password")]),n("td",[n("input",{directives:[{name:"model",rawName:"v-model",value:e.info.password.value,expression:"info.password.value"}],class:[e.info.password.active?"active":"inactive"],attrs:{type:"password",readonly:""},domProps:{value:e.info.password.value},on:{click:function(t){e.showPassword(),e.setWritable(e.info.password)},keyup:function(t){if(!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter"))return null;e.hidePassword(),e.setReadOnly(e.info.password)},input:function(t){t.target.composing||e.$set(e.info.password,"value",t.target.value)}}})])]),n("tr",[n("th",[e._v("Total Uploaders")]),n("td",[e._v(e._s(e.info.totUploaders))])]),n("tr",[n("th",[e._v("Total Files")]),n("td",[e._v(e._s(e.info.totFiles))])]),n("tr",[n("th",[e._v("Total Unseen Files")]),n("td",[e._v(e._s(e.info.totUnseenFiles))])]),n("tr",[n("th",[e._v("Total Seen Files")]),n("td",[e._v(e._s(e.info.totSeenFiles))])]),n("tfoot",[n("tr",[n("td"),n("td",{staticStyle:{"text-align":"right"}},[n("button",{on:{click:function(t){return e.commitChanges()}}},[e._v("CONFIRM CHANGES")])])])])]):e._e()])},V=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("colgroup",[n("col"),n("col")])}],X={name:"PrivateArea",data:function(){return{info:{username:"loading...",email:{value:"loading...",active:!1},nameSurname:{value:"loading...",active:!1},password:{value:"",active:!1},totFiles:"loading...",totSeenFiles:"loading...",totUnseenFiles:"loading...",totUploaders:"loading..."}}},created:function(){var e=this;i.a.get("".concat("/HoneycombStorage/api/","consumerarea/consumerinfo")).then((function(t){e.info.username=t.data.username,e.info.email={value:t.data.email,active:!1},e.info.nameSurname={value:t.data.nameSurname,active:!1},e.info.password={value:t.data.password,active:!1},e.info.totFiles=t.data.totFiles,e.info.totSeenFiles=t.data.totSeenFiles,e.info.totUnseenFiles=t.data.totUnseenFiles,e.info.totUploaders=t.data.totUploaders})).catch((function(e){return console.log(e)}))},methods:{showPassword:function(){event.target.type="text"},hidePassword:function(){event.target.type="password"},setReadOnly:function(e){event.target.readOnly=!0,e.active=!1},setWritable:function(e){event.target.readOnly=!1,e.active=!0},commitChanges:function(){var e={username:this.info.username,nameSurname:this.info.nameSurname.value,email:this.info.email.value,password:this.info.password.value};i.a.put("".concat("/HoneycombStorage/api/","consumerarea/consumer"),e).then((function(e){return console.log(e.status)})).catch((function(e){return console.log(e)}))}}},Z=X,ee=(n("0828"),Object(f["a"])(Z,Q,V,!1,null,"0df0df58",null)),te=ee.exports;a["a"].use(y["a"]);var ne=[{path:"/",name:"Home",component:C},{path:"/Uploader/:username",name:"Uploader",component:K},{path:"/PrivateArea",name:"PrivateArea",component:te}],ae=new y["a"]({routes:ne}),re=ae;a["a"].config.productionTip=!1,new a["a"]({el:"#app",router:re,render:function(e){return e(_)}}).$mount("#app")},"85ec":function(e,t,n){},"91e2":function(e,t,n){"use strict";var a=n("d6fb"),r=n.n(a);r.a},"9cac":function(e,t,n){"use strict";var a=n("519c"),r=n.n(a);r.a},a137:function(e,t,n){"use strict";var a=n("e387"),r=n.n(a);r.a},a185:function(e,t,n){},d6fb:function(e,t,n){},dcf2:function(e,t,n){},e387:function(e,t,n){}});
//# sourceMappingURL=index.f00a6db5.js.map