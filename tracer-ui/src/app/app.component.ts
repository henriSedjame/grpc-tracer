import {Component, OnInit} from '@angular/core';
import { Request } from '@improbable-eng/grpc-web/dist/typings/invoke';
import { grpc } from '@improbable-eng/grpc-web';
import {DashboardService, DashboardServiceClient} from "./grpc/services_pb_service";
import {Empty, TraceDto} from "./grpc/dtos_pb";
import Code = grpc.Code;
import Metadata = grpc.Metadata;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'tracer-ui';

  traces: TraceDto[]= []

  grpcClient!: Request;


  ngOnInit(): void {

    //let request = new Empty()
//
    //this.grpcClient = grpc.invoke(DashboardService.getTraces,{
    //  request: request,
    //  host: "http://localhost:8080",
    //  onEnd: (code: Code, message: string, trailers: Metadata) => {
    //    console.log(`{code : ${code} , message: ${message}}`)
    //  },
    //  onMessage: (trace: TraceDto) => {
    //    this.traces.push(trace)
    //  },
    //})

    let client = new DashboardServiceClient("http://localhost:8080")

    let stream = client.getTraces(new Empty())

    stream.on("data", data => {
      this.traces.push(data)
    })

    stream.on("end", status => {
      console.log(`End with status ${status}`)
    })

    stream.on("status", status => {
      console.log(`On status => ${status}`)
    })

  }

}
