import {Component, Input, OnInit} from '@angular/core';
import {TraceDto} from "../../grpc/dtos_pb";
import SpanDto = TraceDto.SpanDto;

@Component({
  selector: 'app-trace-view',
  templateUrl: './trace-view.component.html',
  styleUrls: ['./trace-view.component.css']
})
export class TraceViewComponent implements OnInit {

  @Input() trace!: TraceDto

  constructor() { }

  ngOnInit(): void {
  }

  public hasError(): boolean {
    return this.trace.getSpansList().some(span => {
      return  span.getLogsList().some(log => log.getLevel() == 3)
    })
  }

  public hasWarning(): boolean {
    return this.trace.getSpansList().some(span => {
      return span.getLogsList().some(log => log.getLevel() == 1)
    })
  }

  public ml(span: SpanDto) {
    return Math.round(((span.getStart() * 70)/(this.trace.getDuration())));
  }

  public w(span: SpanDto) {
    return Math.round( (span.getDuration() * 70)/(this.trace.getDuration()));
  }


}
