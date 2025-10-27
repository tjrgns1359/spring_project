//DOM 객체 연결(html혹은jsp파일안에 있는태그들, 
// 즉 객체들을 자바스크립트와 연결시키는 과정!)
const container= document.getElementById("container");
const menuAdmin=document.getElementById("menuAdmin");
const menuList=document.getElementById("menuList");


//CSRF 토큰과 헤더이름 가져오기 
const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
const csrfHeader = document.querySelector("meta[name='csrf_header']").getAttribute('content');

 