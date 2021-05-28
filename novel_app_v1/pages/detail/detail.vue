<template>
	<view class="_body"  @touchstart="start" @touchend="end"
	  
	  >
		<plugin :section_title="section_title"  :content_text="content_text" :shuming="shuming"></plugin>
	</view>
</template>

<script>
	import Plugin from './Plugin/Plugin.vue';
	import { Request }  from '@/util/Request.js'
	import {
		mapState,mapMutations,
		mapGetters
	} from  'vuex';
	
	let that;
	export default {
		components:{
			Plugin
		},
		data() {
			return {
				section_title:'第一章',
				content_text: '书籍内容加载中',
				shuming: '书籍名字加载中',
			//	prevPageIndexId:null,
				//nextPageIndexId:null,
				indexId: null,
			 
				startData: {
				}
			}
		},
		computed: {
			...mapGetters(['$indexList','$curIndex' ,'$curPage' , '$totalPage' ,'$bookId']),
			bookName() {
				return this.shuming;
			}
		},
		methods: {
			...mapMutations(['setIndexList','setCurIndex','set$CurPage','set$TotalPage']),
			
			initData(indexId) {
				Request({
					key:'get_detail_data',
					url:`/api/book_detail?indexId=${indexId}`,
					success({data}) {
						//滚动到顶部
						uni.pageScrollTo({
							scrollTop:0,
							duration:0
						})
						let p = data.data;
						let res = p.result;
						that.content_text = "\r\n\r\n"+res.bookDetail||'';
						
					}
				})
				
			},
			start(e){
			                    
			    this.startData.clientX=e.changedTouches[0].clientX;
			                 
			    this.startData.clientY=e.changedTouches[0].clientY;
			},
			end(e){
			    // console.log(e)
			    const subX=e.changedTouches[0].clientX-this.startData.clientX;
			    const subY=e.changedTouches[0].clientY - this.startData.clientY;
			    if(subY>50 || subY<-50){
			        console.log('上下滑')
			    }else{
			        if(subX>80){
			            console.log('右滑')
						that.toDetailPage(this.$curIndex-1)
			        }else if(subX<-80){
			            console.log('左滑');
						that.toDetailPage(this.$curIndex+1);
						 
			        }else{
			            console.log('无效')
						
			        }
			    }
			},
			toDetailPage(arrayIndex) {
				console.log(that.indexList)
				console.log(arrayIndex)
				if(arrayIndex<0) {
					uni.showToast({
						icon:'none',
						title:'已经最左边了'
					})
					return;
				} else if(arrayIndex>= this.$indexList.length) {
					 
					if(that.$totalPage <= that.$curPage) {
						uni.showToast({
							icon:'none',
							title:'已经没有了'
						});
					}else {
						Request({
							key: 'forNext',
							url: `/api/book_title?page=${that.$curPage + 1}&bookId=${that.$bookId}&size=200`,
							success({data}) {
								//console.log(data)
								let p = data.data;
								
								//that.NextPage = that.NextPage + 1;
								that.set$CurPage(p.titleList.curPage);
								that.set$TotalPage( p.titleList.totalPage )
								console.log(p.titleList.data.length);
								console.log(that.$indexList.length);
								that.setIndexList( [...that.$indexList, ...p.titleList.data ])
								console.log(that.$indexList.length)
								// that.List = 
								//console.log(p);
								//that.setIndexList(that.List)
								//that.setCurIndex()
								//跳转
								let pp = that.$indexList[parseInt(arrayIndex)];
								//console.log(p)
								console.log(pp)
								// this.shuming = p.bookName;
								that.section_title = pp.downloadTitle||"";
								that.setCurIndex(arrayIndex);
								that.initData(pp.indexId)
							}
						})
					}
						//return;
					 
				} else {
					//console.log(this.$indexList)
					let p = this.$indexList[parseInt(arrayIndex)];
					//console.log(p)
					// this.shuming = p.bookName;
					this.section_title = p.downloadTitle
					that.setCurIndex(arrayIndex);
					that.initData(p.indexId)
					// uni.navigateTo({
					// 	url:`./detail?indexId=${p.indexId}&bookName=${that.shuming}&sectionTitle=${p.downloadTitle}		
					// 	`
					// })
				}
				
			},
		},
		onLoad({
			bookName,
			sectionTitle,
			indexId,
 
		}) {
			that = this;
			that.section_title = sectionTitle||'无';
			that.shuming = bookName||'无';
			
			//that.prevPageIndexId = prevPageIndexId
			//that.nextPageIndexId = nextPageIndexId
			that.indexId = indexId;
			that.initData(indexId);
		}
	}
</script>

<style scoped lang="scss">
	._body {
		padding-top: 14rpx;
	}
</style>
