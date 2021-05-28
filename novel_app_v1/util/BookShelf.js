let KEY = 'BOOK_APP__';
function   saveBook(book) {
	console.log('save_book')
	if(book.bookId == null) return;
	let bookJson = getBook();
	if(bookJson==null) {
		bookJson = {};
	}
	if(bookJson.list==null) {
		bookJson.list = [];
	}
	var found = false;
	for(let idx in bookJson.list) {
		if(bookJson.list[idx].bookId == book.bookId) {
			found = true;
			break;
		}
	}
	if(found == false) {
		bookJson.list.push(book)
		window.localStorage.setItem(KEY,JSON.stringify(bookJson));
	}
}

function  getBook() {
	let p = window.localStorage.getItem(KEY);
	if(p==null || p=='') return {};
	return  p && JSON.parse(p);
}
export {
	getBook,
	saveBook
}