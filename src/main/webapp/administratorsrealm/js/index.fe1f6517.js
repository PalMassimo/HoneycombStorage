(function(t){function e(e){for(var n,i,s=e[0],l=e[1],c=e[2],u=0,m=[];u<s.length;u++)i=s[u],Object.prototype.hasOwnProperty.call(r,i)&&r[i]&&m.push(r[i][0]),r[i]=0;for(n in l)Object.prototype.hasOwnProperty.call(l,n)&&(t[n]=l[n]);d&&d(e);while(m.length)m.shift()();return o.push.apply(o,c||[]),a()}function a(){for(var t,e=0;e<o.length;e++){for(var a=o[e],n=!0,s=1;s<a.length;s++){var l=a[s];0!==r[l]&&(n=!1)}n&&(o.splice(e--,1),t=i(i.s=a[0]))}return t}var n={},r={index:0},o=[];function i(e){if(n[e])return n[e].exports;var a=n[e]={i:e,l:!1,exports:{}};return t[e].call(a.exports,a,a.exports,i),a.l=!0,a.exports}i.m=t,i.c=n,i.d=function(t,e,a){i.o(t,e)||Object.defineProperty(t,e,{enumerable:!0,get:a})},i.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})},i.t=function(t,e){if(1&e&&(t=i(t)),8&e)return t;if(4&e&&"object"===typeof t&&t&&t.__esModule)return t;var a=Object.create(null);if(i.r(a),Object.defineProperty(a,"default",{enumerable:!0,value:t}),2&e&&"string"!=typeof t)for(var n in t)i.d(a,n,function(e){return t[e]}.bind(null,n));return a},i.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return i.d(e,"a",e),e},i.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)},i.p="/administratorsrealm/";var s=window["webpackJsonp"]=window["webpackJsonp"]||[],l=s.push.bind(s);s.push=e,s=s.slice();for(var c=0;c<s.length;c++)e(s[c]);var d=l;o.push([0,"chunk-vendors"]),a()})({0:function(t,e,a){t.exports=a("56d7")},"0283":function(t,e,a){},"034f":function(t,e,a){"use strict";var n=a("85ec"),r=a.n(n);r.a},"0e50":function(t,e,a){"use strict";var n=a("9ea2"),r=a.n(n);r.a},"0f42":function(t,e,a){},"40b2":function(t,e,a){},4367:function(t,e,a){"use strict";var n=a("5822"),r=a.n(n);r.a},"4a95":function(t,e,a){"use strict";var n=a("7821"),r=a.n(n);r.a},"56d7":function(t,e,a){"use strict";a.r(e);a("e260"),a("e6cf"),a("cca6"),a("a79d");var n=a("2b0e"),r=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"app"}},[a("Header"),a("keep-alive",[a("router-view")],1)],1)},o=[],i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{attrs:{id:"header"}},[a("header",[a("img",{attrs:{src:"bee.png",alt:"bee"}}),a("h1",[a("router-link",{attrs:{to:"/"}},[t._v("HONEYCOMB STORAGE")])],1),a("img",{attrs:{src:"honeycomb.png",alt:"honeycomb"}})]),a("nav",[a("ol",[a("li",[a("router-link",{attrs:{to:"/addentity"}},[t._v("Add Entity")])],1),a("li",[a("router-link",{attrs:{to:"/uploadedfiles"}},[t._v("Uploaded Files")])],1),a("li",[a("router-link",{attrs:{to:"/uploaders"}},[t._v("Uploaders")])],1),a("li",[a("router-link",{attrs:{to:"/administrators"}},[t._v("Administrators")])],1),a("li",[a("router-link",{attrs:{to:"/privatearea"}},[t._v("Private Area")])],1)])])])},s=[],l={name:"Header"},c=l,d=(a("0e50"),a("2877")),u=Object(d["a"])(c,i,s,!1,null,"b271fa98",null),m=u.exports,p={name:"App",components:{Header:m}},f=p,h=(a("034f"),Object(d["a"])(f,r,o,!1,null,null,null)),v=h.exports,y=a("8c4f"),b=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("main",{staticClass:"home"},[a("h1",[t._v("Administrator Homepage")]),a("p",[t._v(" Dear administrator, these are the general info about the entire honeycomb storage system. ")]),a("table",[a("tr",[a("th",[t._v("Total Administrators")]),a("td",[t._v(t._s(t.info.totalAdministrators))])]),a("tr",[a("th",[t._v("Total Uploaders")]),a("td",[t._v(t._s(t.info.totalUploaders))])]),a("tr",[a("th",[t._v("Total Consumers")]),a("td",[t._v(t._s(t.info.totalConsumers))])]),a("tr",[a("th",[t._v("Total Uploaded Files")]),a("td",[t._v(t._s(t.info.totalUploadedFiles))])])])])},g=[],_=a("bc3a"),w=a.n(_),S={name:"Home",data:function(){return{info:{}}},created:function(){var t=this;w.a.get("".concat("/api/","administratorarea/generalinfo")).then((function(e){return t.info=e.data})).catch((function(t){return console.log(t)}))}},k=S,x=(a("a514"),Object(d["a"])(k,b,g,!1,null,"a01d9716",null)),F=x.exports,O=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("main",[a("h1",[t._v("Add new Entity")]),a("p",[t._v("Here you can add a new administorator or a new uploader.")]),a("form",{attrs:{method:"post"},on:{submit:t.submitForm}},[a("label",{attrs:{for:"usernameField"}},[t._v("Username:")]),a("input",{directives:[{name:"model",rawName:"v-model",value:t.username,expression:"username"}],attrs:{type:"text",id:"usernameField",name:"username",placeholder:"enter username",required:""},domProps:{value:t.username},on:{input:function(e){e.target.composing||(t.username=e.target.value)}}}),a("label",{attrs:{for:"emailField"}},[t._v("Email:")]),a("input",{directives:[{name:"model",rawName:"v-model",value:t.email,expression:"email"}],attrs:{type:"email",id:"emailField",name:"email",placeholder:"enter email",required:""},domProps:{value:t.email},on:{input:function(e){e.target.composing||(t.email=e.target.value)}}}),a("label",{attrs:{for:"nameSurnameField"}},[t._v("Name, Surname:")]),a("input",{attrs:{type:"text",id:"nameSurnameField",name:"nameSurname",placeholder:"enter name & surname",required:""}}),a("label",{attrs:{for:"passwordField"}},[t._v("Password:")]),a("input",{attrs:{type:"password",id:"passwordField",name:"password",placeholder:"enter password",required:""}}),a("span",[t._v("Entity to create:")]),a("label",{attrs:{for:"radioUploader"}},[a("input",{directives:[{name:"model",rawName:"v-model",value:t.entity,expression:"entity"}],attrs:{type:"radio",id:"radioUploader",value:"uploader"},domProps:{checked:t._q(t.entity,"uploader")},on:{click:function(e){return t.checkUploaderPattern()},change:function(e){t.entity="uploader"}}}),t._v(" Uploader ")]),a("label",{attrs:{for:"radioAdministrator"}},[a("input",{directives:[{name:"model",rawName:"v-model",value:t.entity,expression:"entity"}],attrs:{type:"radio",value:"administrator",id:"radioAdministrator"},domProps:{checked:t._q(t.entity,"administrator")},on:{click:function(e){return t.checkAdministratorPattern()},change:function(e){t.entity="administrator"}}}),t._v(" Administrator ")]),a("input",{attrs:{type:"submit",value:"SUBMIT"}})])])},P=[],q=(a("99af"),a("ac1f"),{name:"AddEntity",data:function(){return{entity:"uploader",username:"",email:""}},methods:{submitForm:function(){document.querySelector("form").action="".concat("/api/","administratorarea/").concat(this.entity)},disableForm:function(){document.querySelector("input[type='submit']").disabled=!0},enableForm:function(){document.querySelector("input[type='submit']").disabled=!1},checkUploaderPattern:function(){var t=/^[a-zA-Z0-9]{4}$/i;t.exec(this.username)?this.enableForm():this.disableForm()},checkAdministratorPattern:function(){this.email===this.username?this.enableForm():this.disableForm()}},watch:{username:function(){"uploader"===this.entity?this.checkUploaderPattern():this.checkAdministratorPattern()},email:function(){"administrator"===this.entity&&this.checkAdministratorPattern()}}}),E=q,U=(a("4367"),Object(d["a"])(E,O,P,!1,null,"917c58ba",null)),A=U.exports,N=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("main",{attrs:{id:"uploaderpage"}},[a("h1",[t._v("Manage Uploaders")]),t._m(0),a("UploaderTable")],1)},C=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("p",[t._v(" In this table are reported all the uploaders of the platform. You can modify their information, except their username. You can also delete them, but be aware: this action "),a("em",[t._v("can not")]),t._v(" be undone! ")])}],$=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("table",[t._m(0),0!==t.uploaders.length?a("tbody",t._l(t.uploaders,(function(e,n){return a("tr",{key:n},[a("td",[t._v(t._s(e.username))]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.email,expression:"uploader.email"}],staticClass:"inactive",attrs:{type:"email",readonly:""},domProps:{value:e.email},on:{input:function(a){a.target.composing||t.$set(e,"email",a.target.value)}}})]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.nameSurname,expression:"uploader.nameSurname"}],staticClass:"inactive",attrs:{type:"text",readonly:""},domProps:{value:e.nameSurname},on:{input:function(a){a.target.composing||t.$set(e,"nameSurname",a.target.value)}}})]),a("td",[""!=e.logo?a("img",{attrs:{src:"data:image/*;base64,".concat(e.logo),alt:"uploader_logo"}}):a("img",{attrs:{src:"default_profile_picture.png"}}),a("label",{staticClass:"active",attrs:{for:"profile_picture".concat(n)}},[t._v(" change "),a("input",{attrs:{type:"file",accept:"image/*",id:"profile_picture".concat(n)},on:{change:function(e){return t.changeImage(n)}}})])]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.password,expression:"uploader.password"}],staticClass:"inactive",attrs:{type:"password",readonly:""},domProps:{value:e.password},on:{input:function(a){a.target.composing||t.$set(e,"password",a.target.value)}}})]),a("td",[a("button",[a("img",{attrs:{src:"closed_lock.png",alt:"closed_lock"},on:{click:function(a){return t.edit(e,n)}}})])]),a("td",[a("button",{on:{click:function(a){return t.commitChanges(e)}}},[a("img",{attrs:{src:"commit_changes.png",alt:"save_in_database"}})])]),a("td",[a("button",{on:{click:function(a){return t.deleteUploader(e.username)}}},[a("img",{attrs:{src:"red_bin.png",alt:"red_bin"}})])])])})),0):a("tbody",{staticStyle:{"margin-top":"5vh","margin-bottom":"5vh"}},[a("em",[t._v(" There are no uploaders yet")])])])},j=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("thead",[a("tr",[a("th",[t._v("Username")]),a("th",[t._v("Email")]),a("th",[t._v("Name, Surname")]),a("th",[t._v("Logo")]),a("th",[t._v("Password")]),a("th",[t._v("Edit")]),a("th",[t._v("Save")]),a("th",[t._v("Delete")])])])}],T=(a("4de4"),a("caad"),a("2532"),{name:"UploaderTable",data:function(){return{uploaders:[]}},created:function(){var t=this;w.a.get("".concat("/api/","administratorarea/uploaders")).then((function(e){t.uploaders=e.data})).catch((function(t){return console.log(t)}))},methods:{commitChanges:function(t){w.a.post("".concat("/api/","administratorarea/uploader"),t).then((function(){return alert("changes saved")})).catch((function(t){return console.log(t)}))},changeImage:function(t){var e=event.target.files[0],a=new FileReader,n=event.target.parentElement.parentElement.querySelector("img");a.readAsDataURL(e);var r=this;a.onload=function(){n.src=a.result,r.uploaders[t].logo=a.result.substring(23)}},deleteUploader:function(t){var e=this;w.a.delete("".concat("/api/","administratorarea/uploader/").concat(t)).then((function(){e.uploaders=e.uploaders.filter((function(e){return e.username!==t}))})).catch((function(t){return console.log(t)}))},edit:function(t,e){event.target.src.includes("closed_lock")?(event.target.src="opened_lock.png",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='email']")).readOnly=!1,document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='email']")).className="active",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='text']")).readOnly=!1,document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='text']")).className="active",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='password']")).readOnly=!1,document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='password']")).className="active",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='password']")).type="text",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td label")).style.display="block",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td img")).style.display="none"):(event.target.src="closed_lock.png",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td:nth-of-type(5) input[type='text']")).type="password",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='email']")).readOnly=!0,document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='email']")).className="inactive",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='text']")).readOnly=!0,document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='text']")).className="inactive",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='password']")).readOnly=!0,document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='password']")).className="inactive",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td label")).style.display="none",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td img")).style.display="inline-block")}}}),M=T,D=(a("a74d"),Object(d["a"])(M,$,j,!1,null,"c9f43160",null)),Y=D.exports,H={name:"UploaderPage",components:{UploaderTable:Y}},I=H,R=Object(d["a"])(I,N,C,!1,null,"90a9bdbc",null),L=R.exports,B=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("main",[a("h1",[t._v("All Uploaded Files")]),a("p",[t._v(" In the table below are reported all the uploaders and the files they have uploaded. You can filter them selecting the two dates below. By default, the period of time is the last month. To see the files of another uploader, just click on his username. "),0===this.uploadedFiles.length?a("em",[t._v(" No file has been uploaded yet.")]):t._e()]),a("div",[a("label",{attrs:{for:"from-day"}},[t._v(" From: "),a("input",{directives:[{name:"model",rawName:"v-model",value:t.from,expression:"from"}],attrs:{type:"date",min:"1970-01-01",id:"from-day"},domProps:{value:t.from},on:{change:function(e){return t.changeCurrentFiles()},input:function(e){e.target.composing||(t.from=e.target.value)}}})]),a("label",{attrs:{for:"to-day"}},[t._v(" To: "),a("input",{directives:[{name:"model",rawName:"v-model",value:t.to,expression:"to"}],attrs:{type:"date",min:"1970-01-01",id:"to-day"},domProps:{value:t.to},on:{change:function(e){return t.changeCurrentFiles()},input:function(e){e.target.composing||(t.to=e.target.value)}}})])]),a("section",[a("table",[t._m(0),0!==t.uploadedFiles.length?a("tbody",t._l(t.uploadedFiles,(function(e,n){return a("tr",{key:e.username},[a("td",{on:{click:function(e){t.setActive(n+1),t.changeCurrentFiles()}}},[t._v(" "+t._s(e.username)+" ")]),a("td",[t._v(t._s(e.uploadedFiles.length))])])})),0):a("tbody",{staticStyle:{display:"flex","flex-flow":"row nowrap","justify-content":"center","align-items":"center"}},[a("em",[t._v(" there are no uploaders yet ")])])]),a("UploadedFilesTable",{attrs:{files:t.currentFiles}})],1)])},W=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("thead",[a("tr",[a("th",[t._v("Uploader")]),a("th",[t._v("Tot Files")])])])}],J=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("table",[t._m(0),0!==t.files.length?a("tbody",t._l(t.files,(function(e,n){return a("tr",{key:n},[a("td",[t._v(t._s(e.name))]),a("td",[t._v(t._s(e.upload))]),a("td",[t._v(t._s(e.totConsumers))])])})),0):a("tbody",{staticStyle:{display:"flex","flex-flow":"row nowrap","justify-content":"center","align-items":"center"}},[a("em",[t._v(" no files were been uploaded in time specified ")])])])},z=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("thead",[a("tr",[a("th",[t._v("Name")]),a("th",[t._v("Uploaded")]),a("th",[t._v("Consumers")])])])}],G={name:"UploadedFilesTable",props:["files"]},Z=G,K=(a("d4f8"),Object(d["a"])(Z,J,z,!1,null,"942981d2",null)),Q=K.exports,V={name:"UploadedFilePage",components:{UploadedFilesTable:Q},data:function(){return{uploadedFiles:[],currentFiles:[],currentUploader:"",from:"",to:""}},created:function(){var t=this;w.a.get("".concat("/api/","administratorarea/uploadedfiles")).then((function(e){0!==e.data.length&&(t.uploadedFiles=e.data,t.currentFiles=t.uploadedFiles[0].uploadedFiles,t.currentUploader=t.uploadedFiles[0].username,t.changeCurrentFiles())})).catch((function(t){console.log(t),alert("it wasn't possible retrieve information")}));var e=new Date;this.from="".concat(e.getFullYear(),"-").concat(e.getMonth()>9?e.getMonth():"0".concat(e.getMonth()),"-01"),this.to="".concat(e.getFullYear(),"-").concat(e.getMonth()+1>9?e.getMonth()+1:"0".concat(e.getMonth()+1),"-01")},methods:{changeCurrentFiles:function(){var t=this;this.currentFiles=this.uploadedFiles.filter((function(e){return e.username===t.currentUploader}))[0].uploadedFiles.filter((function(e){if(""!==t.from&&""!==t.to){var a=new Date(t.from),n=new Date(t.to),r=new Date(e.upload);return r>a&&r<n}return!0}))},setActive:function(t){for(var e=1;e<=this.uploadedFiles.length;e++)e!==t?event.target.parentElement.parentElement.querySelector("tr:nth-of-type(".concat(e,") td:first-child")).className="inactive":(event.target.parentElement.parentElement.querySelector("tr:nth-of-type(".concat(e,") td:first-child")).className="active",this.currentUploader=event.target.parentElement.parentElement.querySelector("tr:nth-of-type(".concat(e,") td:first-child")).innerText)}}},X=V,tt=(a("eab3"),Object(d["a"])(X,B,W,!1,null,"5f801b4c",null)),et=tt.exports,at=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("main",[a("h1",[t._v("Manage Administrators")]),t._m(0),a("AdministratorTable",{attrs:{administrators:t.administrators},on:{"delete-administrator":t.deleteAdministrator}})],1)},nt=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("p",[t._v(" In this table are reported all the administrators of the platform. You can modify their information, except their username. You can also delete them, but be aware: this action "),a("em",[t._v("can not")]),t._v(" be undone! ")])}],rt=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("table",[t._m(0),a("tbody",t._l(t.administrators,(function(e,n){return a("tr",{key:n},[a("td",[t._v(t._s(e.username))]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.nameSurname,expression:"administrator.nameSurname"}],staticClass:"inactive",attrs:{type:"text",readonly:""},domProps:{value:e.nameSurname},on:{input:function(a){a.target.composing||t.$set(e,"nameSurname",a.target.value)}}})]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.password,expression:"administrator.password"}],staticClass:"inactive",attrs:{type:"password",readonly:""},domProps:{value:e.password},on:{input:function(a){a.target.composing||t.$set(e,"password",a.target.value)}}})]),a("td",[a("button",{on:{click:function(a){return t.edit(e,n)}}},[a("img",{attrs:{src:"closed_lock.png",alt:"closed_lock"}})])]),a("td",[a("button",{on:{click:function(a){return t.commitChanges(e)}}},[a("img",{attrs:{src:"commit_changes.png",alt:"save_in_database"}})])]),a("td",[a("button",{on:{click:function(a){return t.deleteAdministrator(e.username)}}},[a("img",{attrs:{src:"red_bin.png",alt:"red_bin"}})])])])})),0)])},ot=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("thead",[a("tr",[a("th",[t._v("Email")]),a("th",[t._v("Name & Surname")]),a("th",[t._v("Password")]),a("th",[t._v("Edit")]),a("th",[t._v("Save")]),a("th",[t._v("Delete")])])])}],it={name:"AdministratorTable",props:["administrators"],methods:{commitChanges:function(t){w.a.post("".concat("/api/","administratorarea/administrator"),t).then((function(){return alert("changes saved!")})).catch((function(t){console.log(t),alert("it wasn't possible to save your changes, please try again later")}))},deleteAdministrator:function(t){var e=this;w.a.delete("".concat("/api/","administratorarea/administrator/").concat(t)).then((function(a){console.log(a.data),e.$emit("delete-administrator",t)})).catch((function(t){console.log(t),alert("it wasn't possible to delete administrator, please try again later")}))},edit:function(t,e){event.target.src.includes("closed_lock")?(event.target.src="opened_lock.png",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='text']")).readOnly=!1,document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='password']")).readOnly=!1,document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='text']")).className="active",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='password']")).className="active",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td input[type='password']")).type="text"):(event.target.src="closed_lock.png",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td:nth-of-type(2) input")).readOnly=!0,document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td:nth-of-type(3) input")).readOnly=!0,document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td:nth-of-type(2) input")).className="inactive",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td:nth-of-type(3) input")).className="inactive",document.querySelector("table tbody tr:nth-of-type(".concat(e+1,") td:nth-of-type(3) input")).type="password")}}},st=it,lt=(a("7ab5"),Object(d["a"])(st,rt,ot,!1,null,"1f38e1a3",null)),ct=lt.exports,dt={name:"AdministratorPage",components:{AdministratorTable:ct},data:function(){return{administrators:[]}},created:function(){var t=this;w.a.get("".concat("/api/","administratorarea/administrators")).then((function(e){return t.administrators=e.data})).catch((function(t){console.log(t),alert("something went wrong...")}))},methods:{deleteAdministrator:function(t){this.administrators=this.administrators.filter((function(e){return e.username!==t}))}}},ut=dt,mt=(a("8f44"),Object(d["a"])(ut,at,nt,!1,null,"e8a9fca8",null)),pt=mt.exports,ft=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("main",[a("h1",[t._v("Private Area")]),a("p",[t._v(" Dear "+t._s(t.administrator.username)+", welcome to your private area! You can change your information in the table below. Click to "),a("span",{on:{click:function(e){return t.confirmChanges()}}},[t._v("save")]),t._v(" changes. ")]),a("table",[a("tr",[a("th",[t._v("Username")]),a("td",[t._v(t._s(t.administrator.username))])]),a("tr",[a("th",[t._v("Name, Surname")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.administrator.nameSurname,expression:"administrator.nameSurname"}],staticClass:"inactive",attrs:{type:"text",readonly:""},domProps:{value:t.administrator.nameSurname},on:{click:function(e){return t.setWritable()},keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.setReadOnly()},input:function(e){e.target.composing||t.$set(t.administrator,"nameSurname",e.target.value)}}})])]),a("tr",[a("th",[t._v("Password")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:t.administrator.password,expression:"administrator.password"}],staticClass:"inactive",attrs:{type:"password",readonly:""},domProps:{value:t.administrator.password},on:{click:function(e){return t.setWritable()},keyup:function(e){return!e.type.indexOf("key")&&t._k(e.keyCode,"enter",13,e.key,"Enter")?null:t.setReadOnly()},input:function(e){e.target.composing||t.$set(t.administrator,"password",e.target.value)}}})])])]),a("form",{attrs:{action:t.logoutLink(),method:"post"}},[a("input",{attrs:{type:"submit",value:"Logout"}})])])},ht=[],vt={name:"PrivateArea",data:function(){return{administrator:{}}},created:function(){var t=this;w.a.get("".concat("/api/","administratorarea/administrator")).then((function(e){return t.administrator=e.data})).catch((function(t){return console.log(t)}))},methods:{setWritable:function(){event.target.className="active",event.target.readOnly=!1,"password"===event.target.type&&(event.target.type="text")},setReadOnly:function(){event.target.className="inactive",event.target.readOnly=!0,event.target.value===this.administrator.password&&(event.target.type="password")},logoutLink:function(){return"".concat("","/logout")},confirmChanges:function(){w.a.post("".concat("/api/","administratorarea/administrator"),this.administrator).then((function(){return alert("changes committed!")})).catch((function(t){console.log(t),alert("It wasn't possible to save your changes. Please try again later")}))}}},yt=vt,bt=(a("4a95"),Object(d["a"])(yt,ft,ht,!1,null,"81c346d8",null)),gt=bt.exports;n["a"].use(y["a"]);var _t=[{path:"/",name:"Home",component:F},{path:"/addentity",name:"AddEntity",component:A},{path:"/uploaders",name:"UploaderPage",component:L},{path:"/administrators",name:"AdministratorPage",component:pt},{path:"/uploadedfiles",name:"UploadedFilesPage",component:et},{path:"/privatearea",name:"PrivateArea",component:gt}],wt=new y["a"]({routes:_t}),St=wt;n["a"].config.productionTip=!1,new n["a"]({router:St,render:function(t){return t(v)}}).$mount("#app")},5822:function(t,e,a){},7410:function(t,e,a){},7821:function(t,e,a){},"7ab5":function(t,e,a){"use strict";var n=a("0283"),r=a.n(n);r.a},"85ec":function(t,e,a){},"8f44":function(t,e,a){"use strict";var n=a("cf69"),r=a.n(n);r.a},9764:function(t,e,a){},"9ea2":function(t,e,a){},a514:function(t,e,a){"use strict";var n=a("7410"),r=a.n(n);r.a},a74d:function(t,e,a){"use strict";var n=a("9764"),r=a.n(n);r.a},cf69:function(t,e,a){},d4f8:function(t,e,a){"use strict";var n=a("0f42"),r=a.n(n);r.a},eab3:function(t,e,a){"use strict";var n=a("40b2"),r=a.n(n);r.a}});
//# sourceMappingURL=index.fe1f6517.js.map