<template>
	<view>
		<view class="_header">
			
		</view>
		<view class="_section">
			<view v-for="i in List">
				<view class="_item"  @tap="itemClick(i.bookId,i.bookName)">
					<view class="_img">
						<image :src="i.imgUrl"></image>
					</view>
					<view class="_right">
						<view class="_title">
							{{ i.bookName }}
						</view>
						<view class="_author">
							{{ i.author }}
						</view>
						<view class="_desc">
							{{ i.bookIntroduction }}
						</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
import { Request } from '../../util/Request.js';
import {
		mapState,mapMutations,
		mapGetters
	} from  'vuex';
	
	
	
let  that = null;
export default {
	data() {
		return {

			List: [
				{
				  "bookId": 0,
				  "websiteId": 0,
				  "bookName": "",
				  "fromWebsiteId": 0,
				  "deleteStatus": false,
				  "author": "",
				  "bookUrl": "",
				  "gmtCreate": "2021-04-16 21:17:20",
				  "gmtModified": "2021-04-16 21:17:20",
				  "imgUrl": "",
				  "alreadyFinished": false,
				  "bookIntroduction": "",
				  "serialVersionUID": 0
				}
			],
			TotalPage: 1,
			CurPage: 1,
			Size:10,
			NextPage: 2,
			

		};
	
	},
	onLoad() {
		that = this;
		Request({
			url: `/api/book_list_current/`,
			success(res) {
				res = res.data;
				//console.log(res)
				let Arr = res.data.books.data;
				that.List = Arr;
				//当前页数
				that.CurPage = res.data.books.curPage;
				//总页数
				that.TotalPage = res.data.books.totalPage;
				
				
			}
		});
	},
	
	methods: {
		...mapMutations(['setIndexList','setCurIndex','set$CurPage','set$TotalPage']),
		getNext() {
			if(that.TotalPage == that.CurPage) {
				console.log('没有更多了');
				uni.showToast({
					icon:'none',
					title:'没有数据了'
				})
				return;
			}
			Request({
				key: that.NextPage,
				url: `/api/book_list_current?page=${that.NextPage}`,
				success(res) {
					res = res.data;
					if(res) {
						let Arr = res.data.books.data;
						that.List = [ ...that.List ,...Arr];
						that.CurPage = res.data.books.curPage;
						that.TotalPage = res.data.books.totalPage;
						that.NextPage = that.CurPage + 1;
					}
				}
			})
		},
		
		
		itemClick(bookId,bookName) {
			console.log('item click',bookId);
			uni.navigateTo({
				url:`../detail/TitleList?bookId=${bookId}&bookName=${bookName}`,
				 
			})
		}
	},
	computed: {
		
	},
	onReachBottom: () => {
		that.getNext();
	},
	onShow() {
		that.set$CurPage(0);
		that.setIndexList([]);
		//that.set$BookId(0);
	}
	
};
</script>

<style  scoped lang="scss">
	
	
	._item {
		padding: 14rpx;
		display: flex;
		image {
			max-width: calc(86rpx * 2);
			max-height: calc(115rpx * 2);
		}
		
		._right {
			padding: 12rpx;
			._title {
				font-weight: bold;
			}
			._author {
				
			}
			._desc {
				max-width:  600rpx;
				max-height: 140rpx;
				overflow: hidden;
				
			}
			
		}
		
	}
	
</style>
