<template>
	<view>
		<view class="_header">
			<image class="_img" src="https://cdn.jsdelivr.net/gh/uxiaohan/GitImgTypecho/Acg/api.vvhan.com[270].jpg" mode="aspectFit"></image>
		</view>
		<view class="_section">
			<view class="u-m-t-20">
				<u-cell-group>
					<u-cell-item icon="star" title="我的书架" @tap="toBookList()"></u-cell-item>
					<!-- <u-cell-item icon="photo" title="切换主题"></u-cell-item> -->
					<u-cell-item icon="coupon" title="关于作者"  @tap='aboutMe'></u-cell-item>
					 
				</u-cell-group>
			</view>
			
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods: {
			toBookList() {
				uni.navigateTo({
					url: '../BookList/BookList'
				})
			},
			aboutMe() {
				uni.showModal({
					title:'关于',
					content:'本软件小说内容来源网络，侵权请联系电话 110'
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	._header {
		display: flex;
		text-align: center;
		justify-content: space-between;
		._img {
			margin: auto;
		}
	}
	._section {
		padding: 14rpx;
		 
	}
</style>
