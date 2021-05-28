<template>
	<view>
		<view class="_header">
			<view>
				<u-navbar  back-text="返回" :title="bookName" ></u-navbar>
			</view>
		</view>
		<view class="_section">
			
		</view>
		<view class="_body" >
			<view class="_item"  v-for="i in List"  :key='i.indexId'  @tap="toDetailPage(i)" >
				<view>
					{{ i.downloadTitle }}
				</view>
			</view>
		</view>
	</view>
</template>

<script >
	import {Request} from '../../util/Request.js';
	import {saveBook,getBook} from '../../util/BookShelf.js'
	import {
		mapState,mapMutations,mapGetters
	} from  'vuex';
	let that = null;
	export default {
		data() {
			return {
				bookName: '',
				TotalPage: 10,
				CurPage: 1,
				NextPage:1,
				bookId:0,
				List: [
					{
					  "indexId": 0,
					  "downloadUrl": "",
					  "downloadTitle": "",
					  "bookId": 0,
					 
					 
					}
				]
			};
		},
		computed: {
			...mapGetters(['$indexList','$curIndex' ,'$curPage' , '$totalPage']),
		},
		methods: {
			...mapMutations(['setIndexList','setCurIndex','set$CurPage','set$TotalPage','set$BookId']),
			
			getNext() {
				if(that.CurPage == that.totalPage) {
					uni.showToast({
						icon:'none',
						title:'没有更多了'
					})
					
					return;
				}
				Request({
					key: that.NextPage,
					url: `/api/book_title?page=${that.$curPage + 1}&bookId=${that.bookId}&size=200`,
					success({data}) {
						console.log(data)
						let p = data.data;
						that.NextPage = that.NextPage + 1;
						that.CurPage = p.titleList.curPage;
						that.totalPage = p.titleList.totalPage;
						that.List = [...that.List, ...p.titleList.data ]
						console.log(p);
						//获取到的数据设置 到 vuex里面
						that.set$CurPage(that.CurPage);
						that.set$TotalPage(that.totalPage)
						that.setIndexList(that.List)
						//that.setCurIndex()
					}
				})
			},
			back() {
				
			},
			toDetailPage(item) {
				let curIdx = null;
				for (let idx in that.List) {
					if(that.List[idx].indexId== item.indexId) {
						curIdx = idx;
						break;
					}
				}
				that.setCurIndex(curIdx)
				uni.navigateTo({
					url:`./detail?indexId=${item.indexId}&bookName=${that.bookName}&sectionTitle=${item.downloadTitle}		
					`
				})
			},
			async save(Json) {
				saveBook(Json);
			},
		 
			 
		},
		onLoad({bookId,bookName}) {
			that = this;
			let id =  bookId;
			that.bookName = bookName;
			if(id==null) {
				console.error('没有获取到小说信息')
			}else {
				
				that.bookId = id;
				that.List = [];
				
				that.getNext();
				//设置 vuex 的 bookID
			    that.set$BookId(bookId)
				
				//异步存入 localstorage
				that.save({
					bookName,
					bookId
				})
				 
			}
			
		},
		onReachBottom() {
			that.getNext();
		}
	}
</script>

<style lang="scss"  scoped>
	._item {
		width: 934rpx;
		height: 106rpx;
		padding: 20rpx;
		padding-top: 28rpx;
		// display: flex;
		border-bottom: 1rpx solid #EFEFEF;
	}
</style>
