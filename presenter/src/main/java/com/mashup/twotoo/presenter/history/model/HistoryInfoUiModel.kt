package com.mashup.twotoo.presenter.history.model

import model.commit.response.CommitResponseDomainModel

data class HistoryInfoUiModel(
    val commitNo: Int = 0,
    val userNo: Int,
    val text: String = "",
    val photoUrl: String = "",
    val createdTime: String = "",
    val partnerComment: String = "",
) {

    companion object {
        val default = HistoryInfoUiModel(
            photoUrl = "https://shop.biumfood.com/upload/1623296512image_product044.jpg",
            createdTime = "20:35",
            text = "오늘 따라 너무 피곤해서 힘들었다!!! 으악 ! \\n 그래도 귀여운 꽃을 키우기 위해 열심히 해야지 ㅎㅎ",
            userNo = 0,
        )
        val empty = HistoryInfoUiModel(
            photoUrl = "",
            createdTime = "",
            text = "",
            userNo = 0,
        )

        // Todo crateTime을 domainmodel에 추가해야함(공통으로 사용가능한지 확인 후)
        fun from(commit: CommitResponseDomainModel): HistoryInfoUiModel {
            return HistoryInfoUiModel(
                photoUrl = commit.photoUrl,
                createdTime = "",
                userNo = commit.userNo,
                text = commit.text,
                partnerComment = commit.partnerComment,
            )
        }
    }
}
