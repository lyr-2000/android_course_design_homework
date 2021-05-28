<template>
	<view id="#first_page_app">
		<view class="_first_page">
			<view class="holder">
				<u-search v-model="keyword" @custom="search" @search="search" @clear="clear" placeholder="输入关键字然后点击搜索" :action-style="{ color: 'white' }"></u-search>
			</view>
		</view>
		<view class="_section">
			<view v-for="i in List">
				<view class="_item" @tap="itemClick( i.bookId, i.bookName ,i.bookUrl)">
					<view class="_img"><image :src="i.imgUrl"></image></view>
					<view class="_right">
						<view class="_title">{{ i.bookName }}</view>
						<view class="_author">{{ i.author }}</view>
						<view class="_desc">{{ i.bookIntroduction }}</view>
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
import { Request } from '@/util/Request.js';

let that = null;
export default {
	data() {
		return {
			keyword: '',

			List: [
				{
					bookId: 0,
					websiteId: 0,
					bookName: '',
					fromWebsiteId: 0,
					deleteStatus: false,
					author: '',
					bookUrl: '',
					gmtCreate: '2021-04-16 21:17:20',
					gmtModified: '2021-04-16 21:17:20',
					imgUrl: '',
					alreadyFinished: false,
					bookIntroduction: '',
					serialVersionUID: 0
				}
			],
			TotalPage: 1,
			CurPage: 1,
			Size: 10,
			NextPage: 2
		};
	},
	methods: {
		search() {
			console.log('search..');
			Request({
				url: `/api/search_novel?keyword=${that.keyword}`,
				success({ data }) {
					let p = data.data;
					console.log(p);
					that.List = p.list;
				}
			});
		},
		clear() {
			console.log('chear..');
		},
		itemClick(bookId,bookName,downloadUrl) {
			console.log(bookName,'-- ',bookId , downloadUrl);
			Request({
				url:'/api/download?url='+downloadUrl,
				success({data}) {
					console.log(data);
					let p = data.data;
					let bookId = p.bookId;
					if(bookId) {
						uni.navigateTo({
							url:`../detail/TitleList?bookId=${bookId}&bookName=${bookName}`
						})
					}else{
						uni.showToast({
							icon:'none',
							title:'出现异常了'
						})
					}
				}
			})
		},
		  
	},
	onLoad() {
		that = this;
	}
};
</script>

<style lang="scss" scoped>
._first_page {
	.holder {
		background-color: #fb7299;
		// border: 1px solid black;
		display: flex;
		padding: 12rpx;
	}
}

._item {
	padding: 14rpx;
	display: flex;
	image {
		max-width: 172rpx;
		max-height: 230rpx;
	}

	._right {
		padding: 12rpx;
		._title {
			font-weight: bold;
		}
		._author {
		}
		._desc {
			max-width: 172rpx;
			max-height: 141rpx;
			overflow: hidden;
		}
	}
}
</style>
