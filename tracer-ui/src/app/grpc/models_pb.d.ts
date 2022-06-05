// package: io.github.hsedjame.grpc.tracing.models
// file: models.proto

import * as jspb from "google-protobuf";

export class Log extends jspb.Message {
  getMessage(): string;
  setMessage(value: string): void;

  getLevel(): Log.LogLevelMap[keyof Log.LogLevelMap];
  setLevel(value: Log.LogLevelMap[keyof Log.LogLevelMap]): void;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Log.AsObject;
  static toObject(includeInstance: boolean, msg: Log): Log.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: Log, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Log;
  static deserializeBinaryFromReader(message: Log, reader: jspb.BinaryReader): Log;
}

export namespace Log {
  export type AsObject = {
    message: string,
    level: Log.LogLevelMap[keyof Log.LogLevelMap],
  }

  export interface LogLevelMap {
    INFO: 0;
    WARNING: 1;
    ERROR: 3;
  }

  export const LogLevel: LogLevelMap;
}

