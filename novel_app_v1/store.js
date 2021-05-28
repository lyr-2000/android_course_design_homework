import Vue from 'vue'
import Vuex from 'vuex'
Vue.use(Vuex)
const store = new Vuex.Store({
    state: {
		indexList:[]   ,//这个是 小说目录页
		curIndex: null, //这个是 数组的 下标
		totalPage: null,
		curPage:null,
		bookId: null,
	},
    mutations: {
		setIndexList(state,payload) {
			//设置小说目录
			// console.log('payload ',payload)
			state.indexList = payload;
			
		},
		setCurIndex(state,payload) {
			//这个是当前的 数组下标
			state.curIndex = payload;
			window.localStorage.setItem('_curIndex',payload)
			
		},
		set$CurPage(state,page) {
			window.localStorage.setItem('_curpage',page)
			state.curPage = page;
		},
		set$TotalPage(state,total) {
			window.localStorage.setItem('_total',total)
			state.totalPage = total;
		},
		set$BookId(state,bookId) {
			state.bookId = bookId
		}
		
	},
	getters:{
		$indexList( { indexList } ) {
			return  indexList
		},
		$curIndex(state) {
			const temp =  state.curIndex ||window.localStorage.getItem('_curIndex');
			return parseInt(temp);
		},
		$totalPage({totalPage} ) {
			const t = totalPage ||window.localStorage.getItem('_total')||0;
			return parseInt(t);
		},
		$curPage({curPage}) {
			const temp = curPage||window.localStorage.getItem('_curpage');
			return parseInt(temp);
		},
		$bookId: ({bookId}) => {
			return bookId
		}
	},
    actions: {}
})
export default store