package ru.skillbranch.devintensive.models

class Chat (
    var id:String,
    var members:MutableList<User> = mutableListOf(),
    var messages:MutableList<BaseMessage> = mutableListOf()) {
}
