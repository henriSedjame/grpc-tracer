// package: io.github.hsedjame.grpc.tracing.dtos
// file: dtos.proto

import * as jspb from "google-protobuf";
import * as models_pb from "./models_pb";

export class Empty extends jspb.Message {
  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): Empty.AsObject;
  static toObject(includeInstance: boolean, msg: Empty): Empty.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: Empty, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): Empty;
  static deserializeBinaryFromReader(message: Empty, reader: jspb.BinaryReader): Empty;
}

export namespace Empty {
  export type AsObject = {
  }
}

export class TraceDto extends jspb.Message {
  getCorrelationid(): string;
  setCorrelationid(value: string): void;

  getLabel(): string;
  setLabel(value: string): void;

  getDuration(): number;
  setDuration(value: number): void;

  getClosed(): boolean;
  setClosed(value: boolean): void;

  clearSpansList(): void;
  getSpansList(): Array<TraceDto.SpanDto>;
  setSpansList(value: Array<TraceDto.SpanDto>): void;
  addSpans(value?: TraceDto.SpanDto, index?: number): TraceDto.SpanDto;

  serializeBinary(): Uint8Array;
  toObject(includeInstance?: boolean): TraceDto.AsObject;
  static toObject(includeInstance: boolean, msg: TraceDto): TraceDto.AsObject;
  static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
  static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
  static serializeBinaryToWriter(message: TraceDto, writer: jspb.BinaryWriter): void;
  static deserializeBinary(bytes: Uint8Array): TraceDto;
  static deserializeBinaryFromReader(message: TraceDto, reader: jspb.BinaryReader): TraceDto;
}

export namespace TraceDto {
  export type AsObject = {
    correlationid: string,
    label: string,
    duration: number,
    closed: boolean,
    spansList: Array<TraceDto.SpanDto.AsObject>,
  }

  export class SpanDto extends jspb.Message {
    getLabel(): string;
    setLabel(value: string): void;

    getStart(): number;
    setStart(value: number): void;

    getDuration(): number;
    setDuration(value: number): void;

    clearLogsList(): void;
    getLogsList(): Array<models_pb.Log>;
    setLogsList(value: Array<models_pb.Log>): void;
    addLogs(value?: models_pb.Log, index?: number): models_pb.Log;

    serializeBinary(): Uint8Array;
    toObject(includeInstance?: boolean): SpanDto.AsObject;
    static toObject(includeInstance: boolean, msg: SpanDto): SpanDto.AsObject;
    static extensions: {[key: number]: jspb.ExtensionFieldInfo<jspb.Message>};
    static extensionsBinary: {[key: number]: jspb.ExtensionFieldBinaryInfo<jspb.Message>};
    static serializeBinaryToWriter(message: SpanDto, writer: jspb.BinaryWriter): void;
    static deserializeBinary(bytes: Uint8Array): SpanDto;
    static deserializeBinaryFromReader(message: SpanDto, reader: jspb.BinaryReader): SpanDto;
  }

  export namespace SpanDto {
    export type AsObject = {
      label: string,
      start: number,
      duration: number,
      logsList: Array<models_pb.Log.AsObject>,
    }
  }
}

