from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional

DESCRIPTOR: _descriptor.FileDescriptor

class RegisterRequest(_message.Message):
    __slots__ = ["key", "checkid"]
    KEY_FIELD_NUMBER: _ClassVar[int]
    CHECKID_FIELD_NUMBER: _ClassVar[int]
    key: str
    checkid: str
    def __init__(self, key: _Optional[str] = ..., checkid: _Optional[str] = ...) -> None: ...

class RegisterReply(_message.Message):
    __slots__ = ["message"]
    MESSAGE_FIELD_NUMBER: _ClassVar[int]
    message: str
    def __init__(self, message: _Optional[str] = ...) -> None: ...
