(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-4346d01a"],{"0725":function(e,n,o){},"5e69":function(e,n,o){"use strict";var t=o("0725"),r=o.n(t);r.a},fbf0:function(e,n,o){"use strict";o.r(n);var t=function(){var e=this,n=e.$createElement,o=e._self._c||n;return o("form",{attrs:{method:"POST"},on:{submit:[function(n){return e.submit()},function(e){e.preventDefault()}]}},[o("h1",[e._v("Add consumer")]),o("input",{directives:[{name:"model",rawName:"v-model",value:e.consumer.username,expression:"consumer.username"}],attrs:{type:"text",name:"username",placeholder:"Enter username",required:""},domProps:{value:e.consumer.username},on:{input:function(n){n.target.composing||e.$set(e.consumer,"username",n.target.value)}}}),o("input",{directives:[{name:"model",rawName:"v-model",value:e.consumer.email,expression:"consumer.email"}],attrs:{type:"email",name:"email",placeholder:"Enter email",required:""},domProps:{value:e.consumer.email},on:{input:function(n){n.target.composing||e.$set(e.consumer,"email",n.target.value)}}}),o("input",{directives:[{name:"model",rawName:"v-model",value:e.consumer.nomeCognome,expression:"consumer.nomeCognome"}],attrs:{type:"text",name:"nomecognome",placeholder:"Enter name and surname",required:""},domProps:{value:e.consumer.nomeCognome},on:{input:function(n){n.target.composing||e.$set(e.consumer,"nomeCognome",n.target.value)}}}),o("input",{directives:[{name:"model",rawName:"v-model",value:e.consumer.password,expression:"consumer.password"}],attrs:{type:"password",name:"password",placeholder:"Enter password",required:""},domProps:{value:e.consumer.password},on:{input:function(n){n.target.composing||e.$set(e.consumer,"password",n.target.value)}}}),o("input",{attrs:{type:"submit",value:"CONFIRM"}})])},r=[],a=o("bc3a"),s=o.n(a),u={name:"AddConsumer",data:function(){return{consumer:{}}},methods:{submit:function(){s.a.post("".concat("/ProgettoTomCat/api/","uploaderarea/consumermanagment"),this.consumer).then((function(e){return console.log("Il server ha risposto con: "+e.status)})).catch((function(e){return console.log(e)}))}}},m=u,c=(o("5e69"),o("2877")),i=Object(c["a"])(m,t,r,!1,null,"76b32c5d",null);n["default"]=i.exports}}]);
//# sourceMappingURL=chunk-4346d01a.c124aa98.js.map