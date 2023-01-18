package org.api.application

class PageResponse<T> {
    var contents: List<T>;
    var page: Int;
    var size: Int;
    var totalCount: Long;

    constructor(contents: List<T>, page: Int, size: Int, totalCount: Long) {
        this.contents = contents;
        this.page = page;
        this.size = size;
        this.totalCount = totalCount;
    }
}