(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4f9eb6fa"],{"4bf8":function(e,t,a){},a0b8:function(e,t,a){"use strict";var r=a("4bf8"),n=a.n(r);n.a},d45d:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("main",[a("h1",[e._v("Private Area")]),a("p",[e._v(" This is your private area: you can change your email address, name, surname and password. Click to "),a("span",{on:{click:function(t){return e.persistChanges()}}},[e._v("save")]),e._v(" your changes. ")]),a("table",[a("tr",[a("th",[e._v("Email Address")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.uploader.email,expression:"uploader.email"}],staticClass:"inactive",attrs:{type:"email",name:"email",readonly:""},domProps:{value:e.uploader.email},on:{input:function(t){t.target.composing||e.$set(e.uploader,"email",t.target.value)}}})]),a("td",[a("button",{on:{click:function(t){return e.changeReadable("email")}}},[e._v("Edit")])])]),a("tr",[a("th",[e._v("Name and Surname")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.uploader.nameSurname,expression:"uploader.nameSurname"}],staticClass:"inactive",attrs:{type:"text",name:"nameSurname",readonly:""},domProps:{value:e.uploader.nameSurname},on:{input:function(t){t.target.composing||e.$set(e.uploader,"nameSurname",t.target.value)}}})]),a("td",[a("button",{on:{click:function(t){return e.changeReadable("nameSurname")}}},[e._v("Edit")])])]),a("tr",[a("th",[e._v("Password")]),a("td",[a("input",{directives:[{name:"model",rawName:"v-model",value:e.uploader.password,expression:"uploader.password"}],staticClass:"inactive",attrs:{type:"password",name:"password",readonly:""},domProps:{value:e.uploader.password},on:{input:function(t){t.target.composing||e.$set(e.uploader,"password",t.target.value)}}})]),a("td",[a("button",{on:{click:function(t){return e.changeReadable("password")}}},[e._v("Edit")])])])]),a("form",{attrs:{action:e.logoutLink(),method:"post"}},[a("input",{attrs:{type:"submit",value:"Logout"}})]),a("form",{attrs:{action:e.actionURL(),method:"POST",enctype:"multipart/form-data"}},[a("p",[e._v(" Do you want "),a("label",{attrs:{for:"profile_picture_file"}},[e._v(" change"),a("input",{attrs:{type:"file",id:"profile_picture_file",name:"logo",accept:"image/*"},on:{change:function(t){return e.showPicture()}}})]),e._v(" your profile picture? ")]),""!==e.profile_picture?a("img",{attrs:{src:e.profile_picture,alt:"new profile picture"}}):e._e(),""!==e.profile_picture?a("label",{attrs:{for:"submit_prof_pic"}},[e._v(" Do you really want set this image as profile picture? "),""!==e.profile_picture?a("input",{attrs:{id:"submit_prof_pic",type:"submit",value:"CONFIRM"}}):e._e()]):e._e()])])},n=[],o=a("bc3a"),i=a.n(o),u={name:"PrivateArea",methods:{actionURL:function(){return"".concat("/api/","uploaderarea/logo")},logoutLink:function(){return"".concat("","/logout")},showPicture:function(){var e=document.querySelector("input[type='file']").files[0],t=new FileReader,a=this;t.readAsDataURL(e),t.onload=function(){a.profile_picture=t.result}},changeReadable:function(e){var t=document.querySelector("input[name='".concat(e,"']"));!0===t.readOnly?(t.readOnly=!1,t.className="active",event.target.style.border="2px inset","password"===e&&(t.type="input")):(t.readOnly=!0,t.className="inactive",event.target.style.border="2px outset","password"===e&&(t.type="password"))},persistChanges:function(){i.a.post("".concat("/api/","uploaderarea/uploader"),this.uploader).then((function(){return alert("Changes save correctly")})).catch((function(e){alert("something went wrong..."),console.log(e)}))}},data:function(){return{uploader:{},profile_picture:""}},created:function(){var e=this;i.a.get("".concat("/api/","uploaderarea/uploader")).then((function(t){return e.uploader=t.data})).catch((function(e){return console.log(e)}))}},s=u,l=(a("a0b8"),a("2877")),c=Object(l["a"])(s,r,n,!1,null,"67a3f550",null);t["default"]=c.exports}}]);
//# sourceMappingURL=chunk-4f9eb6fa.bd706cb6.js.map