package server;

import com.grpc.matrixmult.MatrixRequest;
import com.grpc.matrixmult.MatrixReply;
import com.grpc.matrixmult.MatrixServiceGrpc.MatrixServiceImplBase;

import io.grpc.stub.StreamObserver;

public class MatrixServiceImpl extends MatrixServiceImplBase {

    @Override
    public void addBlock(MatrixRequest request, StreamObserver<MatrixReply> reply) {
        System.out.println("Request received from client:\n" + request);
        int C00 = request.getA00() + request.getB00()*2;
        int C01 = request.getA01() + request.getB01();
        int C10 = request.getA10() + request.getB10();
        int C11 = request.getA11() + request.getB11();
        MatrixReply response = MatrixReply.newBuilder()
                                          .setC00(C00)
                                          .setC01(C01)
                                          .setC10(C10)
                                          .setC11(C11)
                                          .build();
        reply.onNext(response);
        reply.onCompleted();
    }

    @Override
    public void multiplyBlock(MatrixRequest request, StreamObserver<MatrixReply> reply) {
        System.out.println("Request received from client:\n" + request);

        // Perform matrix multiplication
        int A00 = request.getA00();
        int A01 = request.getA01();
        int A10 = request.getA10();
        int A11 = request.getA11();
        int B00 = request.getB00();
        int B01 = request.getB01();
        int B10 = request.getB10();
        int B11 = request.getB11();

        int C00 = A00 * B00 + A01 * B10;
        int C01 = A00 * B01 + A01 * B11;
        int C10 = A10 * B00 + A11 * B10;
        int C11 = A10 * B01 + A11 * B11;

        MatrixReply response = MatrixReply.newBuilder()
                                          .setC00(C00)
                                          .setC01(C01)
                                          .setC10(C10)
                                          .setC11(C11)
                                          .build();
        reply.onNext(response);
        reply.onCompleted();
    }
}
