(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-5e681466"],{"3f42":function(e,t,n){},8418:function(e,t,n){"use strict";var a=n("c04e"),o=n("9bf2"),r=n("5c6c");e.exports=function(e,t,n){var c=a(t);c in e?o.f(e,c,r(0,n)):e[c]=n}},9176:function(e,t,n){"use strict";var a=n("3f42"),o=n.n(a);o.a},"99af":function(e,t,n){"use strict";var a=n("23e7"),o=n("d039"),r=n("e8b5"),c=n("861d"),s=n("7b0b"),u=n("50c4"),i=n("8418"),l=n("65f0"),m=n("1dde"),d=n("b622"),p=n("2d00"),f=d("isConcatSpreadable"),h=9007199254740991,v="Maximum allowed index exceeded",y=p>=51||!o((function(){var e=[];return e[f]=!1,e.concat()[0]!==e})),g=m("concat"),b=function(e){if(!c(e))return!1;var t=e[f];return void 0!==t?!!t:r(e)},_=!y||!g;a({target:"Array",proto:!0,forced:_},{concat:function(e){var t,n,a,o,r,c=s(this),m=l(c,0),d=0;for(t=-1,a=arguments.length;t<a;t++)if(r=-1===t?c:arguments[t],b(r)){if(o=u(r.length),d+o>h)throw TypeError(v);for(n=0;n<o;n++,d++)n in r&&i(m,d,r[n])}else{if(d>=h)throw TypeError(v);i(m,d++,r)}return m.length=d,m}})},afc9:function(e,t,n){"use strict";n.r(t);var a=function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("main",[n("h1",[e._v("Your Consumers")]),n("p",[e._v("Manage your consumers!")]),0!==e.consumers.length?n("table",[e._m(0),n("tbody",e._l(e.consumers,(function(t,a){return n("tr",{key:t.username},[n("td",[n("input",{directives:[{name:"model",rawName:"v-model",value:t.username,expression:"consumer.username"}],staticClass:"inactive",attrs:{type:"text",readonly:""},domProps:{value:t.username},on:{input:function(n){n.target.composing||e.$set(t,"username",n.target.value)}}})]),n("td",[n("input",{directives:[{name:"model",rawName:"v-model",value:t.email,expression:"consumer.email"}],staticClass:"inactive",attrs:{type:"email",readonly:""},domProps:{value:t.email},on:{input:function(n){n.target.composing||e.$set(t,"email",n.target.value)}}})]),n("td",[n("input",{directives:[{name:"model",rawName:"v-model",value:t.nameSurname,expression:"consumer.nameSurname"}],staticClass:"inactive",attrs:{type:"text",readonly:""},domProps:{value:t.nameSurname},on:{input:function(n){n.target.composing||e.$set(t,"nameSurname",n.target.value)}}})]),n("td",[n("input",{directives:[{name:"model",rawName:"v-model",value:t.password,expression:"consumer.password"}],staticClass:"inactive",attrs:{type:"password",readonly:""},domProps:{value:t.password},on:{input:function(n){n.target.composing||e.$set(t,"password",n.target.value)}}})]),n("td",[n("button",{attrs:{title:"edit a consumer"},on:{click:function(t){return e.changeConsumer(a+1)}}},[n("img",{attrs:{src:"closed_lock.png",alt:"closed_lock"}})])]),n("td",[n("button",{attrs:{title:"commit edit changes"},on:{click:function(n){return e.commitChanges(t)}}},[n("img",{attrs:{src:"commit_changes.png",alt:"commit_changes"}})])]),n("td",[n("button",{attrs:{title:"delete definitively a consumer"},on:{click:function(n){return e.deleteConsumer(t.username)}}},[n("img",{attrs:{src:"delete_button.png",alt:"bin"}})])])])})),0)]):n("p",[e._v(" Unfortunately, you don't have any affiliate consumer. Go "),n("router-link",{staticClass:"router-link",attrs:{to:"/AddConsumer"}},[e._v("here")]),e._v(" to add a new consumer ")],1)])},o=[function(){var e=this,t=e.$createElement,n=e._self._c||t;return n("thead",[n("tr",[n("th",[e._v("Username")]),n("th",[e._v("Email")]),n("th",[e._v("Name & Surname")]),n("th",[e._v("Password")]),n("th",[e._v("Edit")]),n("th",[e._v("Commit Changes")]),n("th",[e._v("Delete")])])])}],r=(n("99af"),n("4de4"),n("bc3a")),c=n.n(r),s={name:"MyConsumers",components:{},methods:{changeConsumer:function(e){var t=document.querySelector("table tbody tr:nth-of-type(".concat(e,") td:nth-child(2) input")),n=document.querySelector("table tbody tr:nth-of-type(".concat(e,") td:nth-child(3) input")),a=document.querySelector("table tbody tr:nth-of-type(".concat(e,") td:nth-child(4) input"));!0===a.readOnly?(t.className="active",n.className="active",a.className="active",a.type="text",t.readOnly=!1,n.readOnly=!1,a.readOnly=!1,document.querySelector("table tr:nth-of-type(".concat(e,') td button img[alt="closed_lock"]')).src="opened_lock.png",document.querySelector("table tr:nth-of-type(".concat(e,") td button img[alt='closed_lock']")).alt="opened_lock"):(t.className="inactive",n.className="inactive",a.className="inactive",a.type="password",t.readOnly=!0,n.readOnly=!0,a.readOnly=!0,document.querySelector("table tr:nth-of-type(".concat(e,') td button img[alt="opened_lock"]')).src="closed_lock.png",document.querySelector("table tr:nth-of-type(".concat(e,") td button img[alt='opened_lock']")).alt="closed_lock")},commitChanges:function(e){c.a.put("".concat("/honeycombstorage/api/","uploaderarea/consumer"),e).then((function(){return alert("changes committed!")})).catch((function(){return alert("something went wrong...")}))},deleteConsumer:function(e){var t=this;c.a.delete("".concat("/honeycombstorage/api/","uploaderarea/consumer/").concat(e)).then((function(){alert("consumer deleted correctly"),t.consumers.filter((function(t){return t.username!==e}))})).catch((function(){return alert("something went wrong")}))}},data:function(){return{consumers:[]}},created:function(){var e=this;c.a.get("".concat("/honeycombstorage/api/","uploaderarea/consumers")).then((function(t){return e.consumers=t.data})).catch((function(e){return console.log(e)}))}},u=s,i=(n("9176"),n("2877")),l=Object(i["a"])(u,a,o,!1,null,"bb93dab0",null);t["default"]=l.exports}}]);
//# sourceMappingURL=chunk-5e681466.49172ce3.js.map