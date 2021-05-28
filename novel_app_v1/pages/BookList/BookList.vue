<template>
	<view>
		<view>
			<view class="_header">
				<u-navbar   title="我观看的" ></u-navbar>
			</view>
			<view class="_body">
				<view class="_item" v-for=" i in list"  @tap='toPage(i)'>
					{{ i.bookName }}
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import { saveBook, getBook } from '@/util/BookShelf.js'
	export default {
		data() {
			return {
				list:[]
			}
		},
		methods: {
			toPage(i) {
				uni.navigateTo({
					url:`../detail/TitleList?bookId=${i.bookId}&bookName=${i.bookName}`
				})
			}
		},
		onLoad() {
			let book = getBook();
			this.list = book.list;
		}
	}
</script>

<style scoped lang="scss">
	._item {
		padding: 18rpx;
		padding-left: 21rpx;
		border-bottom: 1rpx solid #f5f5f5;
	}

</style>
