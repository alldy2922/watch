// // window.addEventListener("DOMContentLoaded",() => {
// //     const nc = new NotificationCenter(1);
// // });
// $(document).ready(function () {
//     // Automatically close notification__box after 10 seconds
//     setTimeout(function () {
//         $(".notification__box").fadeOut("slow");
//     }, 10000);
//
// });
// $(".notification__btn").on("click", function () {
//     $(this).closest(".notification__box").fadeOut("slow");
// });
// function getMyNotification(){
//     let param = '&pageNo=' + 1 + '&pageSize=' + 5+ '&orderBy=DESC';
//     let res = ajaxCall(apiDomain + "/api/v1/admin/notification", param, 'GET');
//     if (res.status === "success") {
//         return res.data
//     }else {
//         return [];
//     }
// }
// function getMyUnreadNotificationCount(){
//     let param = 'enabledPage=false&status=1';
//     let res = ajaxCall(apiDomain + "/api/v1/admin/notification/count-unread", param, 'GET');
//     if (res.status === "success") {
//         return res.data
//     }else {
//         return 0
//     }
// }
//
// function markMyNotificationRead(id){
//     let res = ajaxCall(apiDomain + "/api/v1/admin/notification/read", {ids: [id]}, 'PATCH');
//     if (res.status === "success") {
//         loadNotification(getMyUnreadNotificationCount(),getMyNotification())
//         redirectToRequest("/admin/request")
//     }else {
//
//     }
// }
// function iconReadHtml(isRead, ids){
//     return isRead? `<i class="bi bi-check-circle text-success"></i>`:`<i class="bi bi-info-circle text-primary"></i><a href="javascript:markMyNotificationRead(`+ids+`)">`
// }
// function redirectWhenRead(isRead){
//     return !isRead?redirectToRequest("/admin/request") :`#`
// }
// function loadNotification(countUnread, list) {
//     let listData = '';
//     list.forEach((element, index) => {
//         listData +=
//         `<li class="notification-item" onclick="redirectWhenRead(element.isRead)">`+iconReadHtml(element.isRead, element.id)+`
//
//           <div>
//             <h4>`+element.subject+`</h4>
//             <p>`+element.content+`</p>
//           </div>
//         </li>
//         <li>
//           <hr class="dropdown-divider">
//         </li>
//         `
//     })
//     let data =
//         `<a class="nav-link nav-icon" href="#" data-bs-toggle="dropdown">
//             <i class="bi bi-bell"></i>
//             <span class="badge bg-primary badge-number">` + countUnread + `</span>
//           </a>
//           <ul class="dropdown-menu dropdown-menu-end dropdown-menu-arrow notifications">
//             <li class="dropdown-header">
//               You have ` + countUnread + ` new notifications
//               <a href="#"><span class="badge rounded-pill bg-primary p-2 ms-2">View all</span></a>
//             </li>
//             `+listData+`
//             <li class="dropdown-footer">
//               <a href="#">Show all notifications</a>
//             </li>
//           </ul>`
//     $('#notification').html(data);
// }loadNotification(getMyUnreadNotificationCount(),getMyNotification());
//
// function previewNotification(list) {
//     let listData = '';
//     list.forEach((element, index) => {
//     listData +=
//         `<div class="notification__box">
//             <div class="notification__content" onclick="redirectToRequestAndRead('${element.url}',${element.id})">
//                 <div class="notification__text">
//                     <div class="notification__text-title">${element.title}</div>
//                     <div class="notification__text-subtitle">${element.subtitle}</div>
//                 </div>
//             </div>
//
//             <div class="notification__btns">
//                 <button class="notification__btn" type="button" data-dismiss="note-77f39724"
//                         onclick="closeNotification(this)">
//                     <span class="notification__btn-text">Close</span>
//                 </button>
//             </div>
//         </div>
//         `
//     })
//     let data = `<div  class="notification" style="transform: translateY(5%);">
//         `+listData+`
//     </div>`;
//     $('#preview-notification').html(data);
// }
// function closeNotification(button) {
//     $(button).closest(".notification__box").fadeOut("slow");
// }
// function redirectToRequest(url) {
//     window.location.href =  url;
// }
// function redirectToRequestAndRead(url,id) {
//     markMyNotificationRead(id)
//     window.location.href =  url;
// }
//
//
