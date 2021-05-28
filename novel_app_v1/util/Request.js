import Vue from 'vue'
//防止重复请求
let _req_ = {
	reqKey: {
		
	}
};


function Request(config) {
	let url = Vue.baseUrl + '/' + config.url;
	config.url = url;
	let reqKey = config.requestKey||config.reqKey||config.key||'_current_req';
	if(_req_.reqKey[reqKey]) {
		return;
	}
	//防止重复
	_req_.reqKey[reqKey] = reqKey;
	console.log(_req_.reqKey[reqKey]);
	let callback = config.complete;
	//设置回调函数
	config.complete = () => {
		_req_.reqKey[reqKey] = null;
		if(callback) callback();
		console.log('请求完成')
	}
	console.log('url ..', url);
	return uni.request(config);
}

export {
	Request
}
